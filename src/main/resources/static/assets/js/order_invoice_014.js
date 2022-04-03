window.onload = function () {
    $('.modal').hide()
    editData();
}

function onClickSubmitEditOrderInvoice(e) {

}

function onClickSubmitAddOrderInvoice() {

}

function onClickEditOrderInvoice(e) {
    $('#btnSubmitAddOrderInvoice').hide();
    $('#btnSubmitEditOrderInvoice').show();
    $('#titleModelOrderInvoice').html('Sửa đặt hàng NCC')
    $('#btnSubmitAddOrderInvoice').attr('data-id', e.dataset.id)
    $('#bodyModalOrderInvoice').html('');
    $.ajax({
        url: '/order-invoice/component/add-order-invoice?id=' + e.dataset.id,
        success: function (html) {
            $('#bodyModalOrderInvoice').html(html);
        }
    })
}

function onClickAddOrderInvoice() {
    $('#btnSubmitAddOrderInvoice').show();
    $('#btnSubmitEditOrderInvoice').hide();
    $('#titleModelOrderInvoice').html('Thêm đặt hàng NCC')
    $('#bodyModalOrderInvoice').html('');
    $.ajax({
        url: '/order-invoice/component/add-order-invoice?id=-1',
        success: function (html) {
            $('#bodyModalOrderInvoice').html(html);
        }
    })
}

function onAddProductOrderInvoice(e) {
    let count = $('.tr-order-product').length;
    if (count < 20) {
        $.ajax({
            url: '/order-invoice/component/add-product-order-invoice?number=' + count,
            success: function (html) {
                $('#bodyAddProductOrderInvoice').append(html);
            }
        })
        if (count == 19) {
            $(e).hide();
        }
    }
}

function editData() {
    let e = {};
    $(".table-edits tr").editable({
        dropdowns: {product: [{"value": "1234", "text": "234"}, "Female"]},
        edit: function (t) {
            $(".edit i", this).removeClass("fa-pencil-alt").addClass("fa-save btnProductSave").attr("title", "Save")
        },
        save: function (t) {
            $(".edit i", this).removeClass("fa-save btnProductSave").addClass("fa-pencil-alt").attr("title", "Edit"), this in e && (e[this].destroy(), delete e[this])
        },
        cancel: function (t) {
            $(".edit i", this).removeClass("fa-save btnProductSave").addClass("fa-pencil-alt").attr("title", "Edit"), this in e && (e[this].destroy(), delete e[this])
        }
    })
    setTimeout(function () {
        editData();
    }, 10);
}