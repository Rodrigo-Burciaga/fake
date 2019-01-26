$(document).ready(function () {
    $(".button-collapse").sideNav();
    $('select').material_select();

    if ($("#formLeftBar\\:actualMenu").text() !== "") {
        var actual = "#formLeftBar\\:";
        actual += $("#formLeftBar\\:actualMenu").text();
        $(actual).addClass("activo");
    }
    $("label").removeClass("active");
    $('.tooltipped').tooltip({delay: 50});
    $('.modal').modal();
});

function cerrar() {
    $('.button-collapse').sideNav('hide');
}

