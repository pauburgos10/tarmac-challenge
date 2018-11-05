var Application = function () {
    this.dataHandler = new DataHandler();
    this.drawFilters();
    this.registerEvents();
    this.searchWithFilters();    
}

Application.prototype.registerEvents = function () {
    $(".buscar").click(this.search.bind(this));
    $(".next").click(this.askNextPage.bind(this));
    $(".prev").click(this.askPrevPage.bind(this));
}


Application.prototype.drawFilters = function () {
    this.drawFilterCities();
    this.drawFilterRoles();
}

Application.prototype.drawFilterCities = function () {
    $("#filtro-ciudad").empty();
    this.loadDefaultOptions("filtro-ciudad", "City");
    this.loadOptionAll("filtro-ciudad");

    this.dataHandler.getCities();
}

Application.prototype.drawFilterRoles = function () {
    $("#filtro-role").empty();
    this.loadDefaultOptions("filtro-role", "Role");
    this.loadOptionAll("filtro-role")

    this.dataHandler.getRoles();
}

Application.prototype.loadDefaultOptions = function (filterId, defaultOpt) {
    var opcionDefault = $("<option/>").text(defaultOpt).val(0).prop("disabled", true).prop("selected", true);
    opcionDefault.appendTo("#" + filterId);
}

Application.prototype.loadOptionAll = function (filterId) {
    var allOption = $("<option/>").text("All").val(1);
    allOption.appendTo("#" + filterId);
}

Application.prototype.search = function () {
    this.dataHandler.page = 1;
    this.searchWithFilters();    
}

Application.prototype.searchWithFilters = function () {
    if ($("#filtro-role option:selected").val() === "1" || $("#filtro-role option:selected").val() === "0") {
        var roleFilter = null;
    } else {
        var roleFilter = $("#filtro-role option:selected").val();
    }

    if ($("#filtro-ciudad option:selected").val() === "1" || $("#filtro-ciudad option:selected").val() === "0") {
        var cityFilter = null;
    } else {
        var cityFilter = $("#filtro-ciudad option:selected").val();
    }

    this.dataHandler.getEmployeesFiltered(roleFilter, cityFilter);
}

Application.prototype.askNextPage = function () {
    this.dataHandler.moveDirection("next");
    this.dataHandler.fetchNextPage();
}

Application.prototype.askPrevPage = function () {
    this.dataHandler.moveDirection("prev");
    this.dataHandler.fetchPrevPage();
}


var application = new Application();



