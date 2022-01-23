$(document).ready(function() {
    var table = $("#datatable").DataTable({
        paging: false,
        lengthChange: !1,
        buttons: ["copy", "excel", "pdf", "colvis"],

        scrollY: "300px",
        scrollX: true,
        scrollCollapse: true,

        info: false,
    });
    table.buttons().container().appendTo("#datatable_wrapper .col-md-6:eq(0)"),
        $(".dataTables_length select").addClass("form-select form-select-sm");
    $("body").find(".dataTables_scrollBody").addClass("scrollbar");
});