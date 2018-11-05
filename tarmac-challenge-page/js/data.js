
function DataHandler() {

    this.page = 1;
    this.size = 10;
    this.urlPrev = null;
    this.urlNext = null;
    this.prevPageList = [];
    this.currentList = [];
    this.nextPageList = [];
    this.filterRole = null;
    this.filterCity = null;

    this.getCities = function () {
        console.log("Getting cities...");
        const url = "http://localhost:8080/employee/listCities";
        var self = this;
        fetch(url, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => { console.log(json); self.loadCities(json) })
            .catch(() => console.log("Can’t access " + url));

    };

    this.loadCities = function (cities) {
        cities.forEach(function (city) {
            var newOption = $("<option/>").text(city).val(city);
            newOption.appendTo("#filtro-ciudad");
        });
    };

    this.getRoles = function () {
        console.log("Getting roles...");
        const url = "http://localhost:8080/employee/listRoles";
        var self = this;
        fetch(url, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(json => { console.log(json); self.loadRoles(json) })
            .catch(() => console.log("Can’t access " + url));

    };

    this.loadRoles = function (roles) {
        roles.forEach(function (role) {
            var newOption = $("<option/>").text(role).val(role);
            newOption.appendTo("#filtro-role");
        });
    };

    this.moveDirection = function (direction) {
        if (direction === "next" && this.nextPageList.length !== 0) {
            this.page++;
            this.urlNext = this.buildUrl(1);
            this.prevPageList = this.currentList;
            this.enableDisableNavigation("prev", true);
            this.currentList = this.nextPageList;
            this.loadEmployees(this.nextPageList);
            this.nextPageList = []; 
            this.fetchNextPage();         
        } else if (direction === "prev" && this.prevPageList.length !== 0) {
            this.page--;
            this.urlPrev = this.buildUrl(-1);
            this.nextPageList = this.currentList;
            this.enableDisableNavigation("next", true);
            this.currentList = this.prevPageList;
            this.loadEmployees(this.prevPageList);
            this.prevPageList = []; 
            this.fetchPrevPage();           
        } 
        
    }

    this.enableDisableNavigation = function (direction, enable){
        if (enable){
            $("."+ direction).removeClass("disabled");
        } else {
            $("."+direction).addClass("disabled");
        }        
    }
   
    this.buildUrl = function (add) {
        var url;
        var pageTofetch = this.page + add;

        if (pageTofetch > 0) {
            if (this.filterRole == null && this.filterCity == null) {
                url = "http://localhost:8080/employee/list?page=" + pageTofetch + "&size=" + this.size;
            } else if (this.filterRole != null && this.filterCity != null) {
                url = "http://localhost:8080/employee/list/" + this.filterRole + "/" + this.filterCity + "?page=" + pageTofetch + "&size=" + this.size;
            } else if (this.filterRole != null) {
                url = "http://localhost:8080/employee/getByRole?role=" + this.filterRole + "&page=" + pageTofetch + "&size=" + this.size;
            } else {
                url = "http://localhost:8080/employee/getByCity?city=" + this.filterCity + "&page=" + pageTofetch + "&size=" + this.size;
            }
        } else {
            this.enableDisableNavigation("prev", false);
        }
        return url;
    };

    this.getEmployeesFiltered = function (role, city) {

        console.log("Obteniendo empleados filtrados...page=" + this.page + "size="
            + this.size + "role=" + role + "city=" + city);
        this.filterRole = role;
        this.filterCity = city;

        var url = this.buildUrl(0);
        this.urlNext = this.buildUrl(1);
        this.urlPrev = this.buildUrl(-1);

        console.log("url="  + url);
        
        if (url != null) {
            var self = this;
            fetch(url, {
                method: 'GET'
            })
                .then(response => response.json())
                .then(json => { console.log(json); self.loadEmployees(json) })
                .catch(() => console.log("Can’t access " + url));
        }
        
        if (this.urlNext != null) {
            this.fetchNextPage();
        }

        if (this.urlPrev != null) {
            this.fetchPrevPage();
        }
    };


    this.loadEmployees = function (employees) {
        var self = this;
        //empty container
        $(".flex").empty();
        var cards = [];
        console.log("loading employees " + employees.length);
        //When no data found
        if (employees.length === 0) {
            console.log("No employees " + employees.length);
            cards.push($("<span/>").attr("class", "alert").html("No data found"));
            this.enableDisableNavigation("next", false);
            this.enableDisableNavigation("prev", false);
        } else {
            //draw each employee card
            this.currentList = employees;
            employees.forEach(function (empleado) {
                cards.push(self.buildEmployeeCard(empleado));
            });            
        }

        cards.forEach(function(card) {
            card.appendTo(".flex");
        });

    };

    this.fetchPrevPage = function () {
        console.log("urlPrev=" + this.urlPrev);
        if (this.urlPrev != null) {
            self = this;
            fetch(self.urlPrev, {
                method: 'GET'
            })
                .then(response => response.json())
                .then(json => { console.log(json); self.loadPrev(json) })
                .catch(() => { self.nextPageList = null; console.log("Can’t access " + self.urlPrev); });
        } 
    };


    this.loadPrev = function (employees) {
        this.prevPageList = employees;
        if (this.prevPageList.length === 0){
            this.enableDisableNavigation("prev", false);
        } else {
            this.enableDisableNavigation("prev", true);//$(".prev").removeClass("disabled");
        }
    };

    this.fetchNextPage = function () {
        console.log("urlNext=" + this.urlNext);
        if (this.urlNext != null) {
            self = this;
            fetch(self.urlNext, {
                method: 'GET'
            })
                .then(response => response.json())
                .then(json => { console.log(json); self.loadNext(json) })
                .catch(() => { self.nextPageList = null; console.log("Can’t access " + self.urlNext); });
        }
    };

    this.loadNext = function (employees) {
        this.nextPageList = employees;
        if (this.nextPageList.length === 0){
            this.enableDisableNavigation("next", false);
        } else {
            this.enableDisableNavigation("next", true);
        }
    }

    this.buildEmployeeCard = function (employee) {
        var self = this;
        // Creamos el elemento de empleado, asignandole cada atributo del empleado que corresponda
        var card = $(`
        <div class="flex-item" id=${employee.name}>
            <img class="image" src="${employee.picture_url}">
            <div class="information">
                <div class="name-container">
                    <h4 class="name">${employee.name}</h4>                
                </div>
                <div class="information-container">
                    <div class="city">${employee.city}</div>
                    <div class="role">${employee.role}</div>
                </div>
            </div>        
        </div>
        `);

        //card.appendTo(".flex");
        return card;
    };
}

