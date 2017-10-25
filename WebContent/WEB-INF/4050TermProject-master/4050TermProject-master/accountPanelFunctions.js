window.onload = function(){
    toggleInfo();
    reservedAvailableToggle();
};

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});


function toggleInfo() {

    var toggleButton = document.getElementById("toggleButton");
    var adminPane = document.getElementById("adminPane");
    var userPane = document.getElementById("userPane");
    var panelTitle = document.getElementById("panelTitle");


    if(toggleButton.innerHTML === ("admin")){
        adminPane.style.display = "block";
        userPane.style.display = "none";
        toggleButton.innerHTML = "user";
        panelTitle.innerHTML = "Admin Account Preview";
    }
    else{
        adminPane.style.display = "none";
        userPane.style.display = "block";
        toggleButton.innerHTML = "admin";
        panelTitle.innerHTML = "User Account Preview";

    }

}

function reservedAvailableToggle(){
        var availableLink = document.getElementById("availableLink");
        var tableTitle = document.getElementById("tableTitle");
        var availableVehicles = document.getElementById("availableVehicles");
        var reservedVehicles = document.getElementById("reservedVehicles");


     if(availableLink.innerHTML === ("Available")){
        tableTitle.innerHTML = '<a href="#" id="availableLink" onclick="reservedAvailableToggle()">Reserved</a> / <span style="font-weight: bold">Available</span>';
        reservedVehicles.style.display = "none";
        availableVehicles.style.display = "block";

    }
    else{
        tableTitle.innerHTML = '<span style="font-weight: bold">Reserved</span> / <a href="#" id="availableLink" onclick="reservedAvailableToggle()">Available</a>';
        reservedVehicles.style.display = "block";
        availableVehicles.style.display = "none";
    }
}
