// Create our number formatter.
const formatter = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
    // These options are needed to round to whole numbers if that's what you want.
    //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
    //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
});

window.onload = function () {
    $('.modal').hide()
    editData();
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

function onChangeInvoiceOrder(e) {
    let value = e.value + '';
    let id, supplierId;
    if (value !== null && value !== undefined && value !== '') {
        id = value.substring(0, value.indexOf('@'));
        supplierId = value.substring(value.includes('@') + 1)
    } else {
        return;
    }
    $('#ip-add-supplier option[value=' + supplierId + ']').attr('selected', true).change();
    $.ajax({
        url: '/api/invoice?orderId=' + id,
        method: 'GET',
        success: function (data) {
            $("#ip-add-product option:selected").attr('selected', false).change();
            $(data).each((index, obj) => {
                $('#ip-add-product option[value=' + obj.productId + ']').attr('selected', true)
                $('#ip-add-product').change();
                $('#ip-add-product-quantity-' + obj.productId).val(obj.quantity)
            })
        },
        error: function (error) {

        }
    })
}

function onChangeAddProduct(e) {
    let arrSelect = getSelectValues(e);
    $(arrSelect).each((index, data) => {
        let invoiceDetail = $('#invoice-detail');
        if (invoiceDetail.children('#' + data.id).length === 0) {
            invoiceDetail.append('<div class="row" id="' + data.id + '">\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <label for="ip-add-product-quantity-' + data.id + '">\n' +
                data.name +
                '        </label>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="1" onchange="onChangeTotal()" value="1" id="ip-add-product-quantity-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Số lượng...."\n' +
                '               class="form-control add-product-quantity-invoice"/>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="0" onchange="onChangeTotal()" value="0" id="ip-add-product-price-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Giá nhập...."\n' +
                '               class="form-control add-product-price-invoice"/>\n' +
                '    </div>\n' +
                '</div>'
            )
        }
    })
    $('#invoice-detail .row').each((index, data) => {
        let flag = false;
        for (let i = 0; i < arrSelect.length; i++) {
            if (arrSelect[i].id === data.id) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            $(data).prop('outerHTML', '')
        }
    })
    onChangeTotal();
}

function onChangeInvoiceOrderEdit(e) {
    let value = e.value + '';
    let id, supplierId;
    if (value !== null && value !== undefined && value !== '') {
        id = value;
    } else {
        return;
    }
    $('#ip-edit-supplier option[value=' + supplierId + ']').attr('selected', true).change();
    $.ajax({
        url: '/api/invoice?orderId=' + id,
        method: 'GET',
        success: function (data) {
            $(data).each((index, obj) => {
                $('#bodyAddProduct').html('');
                $.ajax({
                    url: '/invoice/component/edit-product?number='
                        + (index + 1)
                        + '&productId=' + (obj.productId)
                        + '&quantity=' + (obj.quantity)
                        + '&quantityActual=' + (obj.quantityActual)
                        + '&price=' + (obj.price),
                    success: function (htm) {
                        $('#bodyAddProduct').append(htm);
                    }
                })
            })
        }
    })
}

function onChangeAddProductEdit(e) {
    let arrSelect = getSelectValues(e);
    $(arrSelect).each((index, data) => {
        let invoiceDetail = $('#invoice-edit-detail');
        if (invoiceDetail.children('#edit' + data.id).length === 0) {
            invoiceDetail.append('<div class="row" id="edit' + data.id + '">\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <label for="ip-edit-product-quantity-' + data.id + '">\n' +
                data.name +
                '        </label>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="1" onchange="onChangeTotalEdit()" value="1" id="ip-edit-product-quantity-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Số lượng...."\n' +
                '               class="form-control edit-product-quantity-invoice"/>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="0" onchange="onChangeTotalEdit()" value="0" id="ip-edit-product-price-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Giá nhập...."\n' +
                '               class="form-control edit-product-price-invoice"/>\n' +
                '    </div>\n' +
                '</div>'
            )
        }
    })
    $('#invoice-edit-detail .row').each((index, data) => {
        let flag = false;
        for (let i = 0; i < arrSelect.length; i++) {
            if (data.id.includes(arrSelect[i].id)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            $(data).prop('outerHTML', '')
        }
    })

}

function getSelectValues(select) {
    let result = [];
    let options = select && select.options;
    let opt;
    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];
        if (opt.selected) {
            result.push({"id": opt.value, "name": opt.text});
        }
    }
    return result;
}

function onClickAddInvoice() {
    let getInvoiceOrderCode = document.getElementById('ip-edit-order-code');
    let getInvoiceShip = document.getElementById('ip-edit-ship');
    let getSupplier = document.getElementById('ip-edit-supplier');
    let getInvoiceDiscount = document.getElementById('ip-edit-invoice-discount');
    let getInvoicePaid = document.getElementById('ip-edit-invoice-paid');
    let getInvoiceNote = document.getElementById('ip-edit-invoice-note');
    let getAllProductId = document.getElementsByClassName('ip-edit-product');
    let getAllProductQuantity = document.getElementsByClassName('ip-edit-quantity');
    let getAllProductQuantityActual = document.getElementsByClassName('ip-edit-quantity-actual');
    let getAllProductPrice = document.getElementsByClassName('ip-edit-price');
    let obj = valueDateInvoice(
        '',
        getInvoiceOrderCode,
        getInvoiceShip,
        getSupplier,
        getInvoiceDiscount,
        getInvoicePaid,
        getInvoiceNote,
        getAllProductId,
        getAllProductQuantity,
        getAllProductQuantityActual,
        getAllProductPrice
    )
    if (obj !== null && obj !== undefined && onChangeQuantityOrPrice()) {
        $.ajax({
            url: '/api/invoice',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (data) {
                toastSuccess('Thành công', 'Đã thêm mới nhập hàng')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (error) {
                if (error.responseJSON.vn === null || error.responseJSON.vn === undefined) {
                    let message = error.responseJSON.message + '';
                    message = message.substring(message.indexOf(':') + 1)
                    toastDanger('Lỗi', message);
                    return;
                }
                toastDanger('Lỗi', error.responseJSON.vn);
            }
        })
    }
}

function onClickOpenModalAdd() {
    $('#btnSubmitAddInvoice').show();
    $('#btnSubmitEditInvoice').hide();
    $('#titleModal').html('Thêm hoá đơn nhập hàng')
    $.ajax({
        url: '/invoice/component/edit-invoice?id=',
        success: function (html) {
            $('#editInvoice').html(html)
            $.ajax({
                url: '/invoice/component/add-product?number=1',
                success: function (tr) {
                    $('#bodyAddProduct').append(tr);
                }
            })
        },
        error: function (e) {
            $('#modal-edit-invoice').modal('hide');
        }
    })
}

function onAddProduct() {
    let count = $('.tr-product').length + 1
    if (count < 20) {
        $.ajax({
            url: '/invoice/component/add-product?number=' + count,
            success: function (tr) {
                $('#bodyAddProduct').append(tr);
            }
        })
        if (count === 19) {
            $('#btnAddProduct').hide();
        }
    }
}


function onClickEditInvoice(e) {
    $('#btnSubmitAddInvoice').hide();
    $('#btnSubmitEditInvoice').show();
    $('#btnSubmitEditInvoice').attr('data-id', e.dataset.id)
    $('#titleModal').html('Cập nhật hoá đơn nhập hàng')
    $.ajax({
        url: '/invoice/component/edit-invoice?id=' + e.dataset.id,
        success: function (html) {
            $('#editInvoice').html(html)
            $.ajax({
                url: '/api/invoice?invoiceId=' + e.dataset.id,
                success: function (data) {
                    $(data).each((index, obj) => {
                        $.ajax({
                            url: '/invoice/component/edit-product?number='
                                + (index + 1)
                                + '&productId=' + (obj.productId)
                                + '&quantity=' + (obj.quantity)
                                + '&quantityActual=' + (obj.quantityActual)
                                + '&price=' + (obj.price),
                            success: function (htm) {
                                $('#bodyAddProduct').append(htm);
                            }
                        })
                    })
                }
            })
        },
        error: function (e) {
            $('#modal-edit-invoice').modal('hide');
        }
    })
}

function onClickSubmitEditInvoice(e) {
    let getInvoiceOrderCode = document.getElementById('ip-edit-order-code');
    let getInvoiceShip = document.getElementById('ip-edit-ship');
    let getSupplier = document.getElementById('ip-edit-supplier');
    let getInvoiceDiscount = document.getElementById('ip-edit-invoice-discount');
    let getInvoicePaid = document.getElementById('ip-edit-invoice-paid');
    let getInvoiceNote = document.getElementById('ip-edit-invoice-note');
    let getAllProductId = document.getElementsByClassName('ip-edit-product');
    let getAllProductQuantity = document.getElementsByClassName('ip-edit-quantity');
    let getAllProductQuantityActual = document.getElementsByClassName('ip-edit-quantity-actual');
    let getAllProductPrice = document.getElementsByClassName('ip-edit-price');
    let obj = valueDateInvoice(
        e.dataset.id,
        getInvoiceOrderCode,
        getInvoiceShip,
        getSupplier,
        getInvoiceDiscount,
        getInvoicePaid,
        getInvoiceNote,
        getAllProductId,
        getAllProductQuantity,
        getAllProductQuantityActual,
        getAllProductPrice
    )
    if (obj !== null && obj !== undefined && onChangeQuantityOrPrice()) {
        $.ajax({
            url: '/api/invoice',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (data) {
                toastSuccess('Thành công', 'Đã cập nhật thông tin nhập hàng')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (error) {
                console.log(error)
                toastDanger('Lỗi', error.responseJSON.vn);
            }
        })
    }
}

function valueDateInvoice(
    id,
    getInvoiceOrderCode,
    getInvoiceShip,
    getSupplier,
    getInvoiceDiscount,
    getInvoicePaid,
    getInvoiceNote,
    getAllProductId,
    getAllProductQuantity,
    getAllProductQuantityActual,
    getAllProductPrice,
) {
    // if (getInvoiceCode === null || getInvoiceCode === undefined || getInvoiceCode.value === '') {
    //     toastDanger('Lỗi', 'Vui lòng nhập mã nhập hàng');
    //     return;
    // }
    // if (getInvoiceCode.value.length < 5 || getInvoiceCode.value.length > 50) {
    //     toastDanger('Lỗi', 'Mã nhập hàng phải từ 10 đến 50 ký tự');
    //     return;
    // }
    if (getSupplier === null || getSupplier === undefined || getSupplier.value === '') {
        toastDanger('Lỗi', 'Vui lòng chọn nhà cung cấp');
        return;
    }
    if (getInvoiceDiscount.value === null || getInvoiceDiscount.value === undefined) {
        getInvoiceDiscount.value = 0
    }
    if (Number(getInvoiceDiscount.value) || getInvoiceDiscount.value === '0') {
        if (Number(getInvoiceDiscount.value) < 0) {
            toastDanger('Lỗi', 'Số tiền giảm giá phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền giảm giá phải là một số nguyên')
        return;
    }
    if (getInvoicePaid.value === null || getInvoicePaid.value === undefined) {
        getInvoicePaid.value = 0
    }
    if (Number(getInvoicePaid.value) || getInvoicePaid.value === '0') {
        if (Number(getInvoicePaid.value) < 0) {
            toastDanger('Lỗi', 'Số tiền trả NCC phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền trả NCC phải là một số nguyên')
        return;
    }
    if (getInvoiceShip === null || getInvoiceShip === undefined || getInvoiceShip.value === '') {
        toastDanger('Lỗi', 'Vui lòng nhập người giao hàng')
        return;
    }
    $('.btnProductSave').click();
    let detail = [];
    if (getAllProductId !== null && getAllProductId !== undefined) {
        for (let i = 0; i < getAllProductId.length; i++) {
            let obj = {
                "productId": getAllProductId[i].value,
                "quantity": getAllProductQuantity[i].innerHTML,
                "quantityActual": getAllProductQuantityActual[i].innerHTML,
                "price": getAllProductPrice[i].innerHTML,
            };
            let flag = true;
            if (obj.productId === null || obj.productId === undefined || obj.productId === '' || obj.productId === 'null') {
                flag = false;
            } else if (obj.quantity === null || obj.quantity === undefined || obj.quantity === '' || obj.quantity === '0' || obj.quantity === 'null') {
                flag = false;
            } else if (Number(obj.quantity) < 1) {
                toastDanger('Lỗi', 'Số lượng trên hoá đơn phải là một số nguyên')
                return;
            } else if (obj.quantityActual === null || obj.quantityActual === undefined || obj.quantityActual === '' || obj.quantityActual === 'null' || obj.quantityActual === '0') {
                flag = false;
            } else if (Number(obj.quantityActual) < 1) {
                toastDanger('Lỗi', 'Số lượng nhập phải là một số nguyên')
                return;
            } else if (obj.price === null || obj.price === undefined || obj.price === '' || obj.price === 'null' || obj.price === '0') {
                flag = false;
            } else if (Number(obj.price) < 1) {
                toastDanger('Lỗi', 'Giá tiền phải là một số nguyên')
                return;
            }
            if (flag) {
                detail.push(obj);
            }
        }
    }
    if (detail.length === 0) {
        toastDanger('Lỗi', 'Vui lòng chọn sản phẩm nhập');
        return;
    }
    let invoiceOrderCode = getInvoiceOrderCode.value + '';
    return {
        "invoiceId": id,
        "shipper": getInvoiceShip.value,
        "invoiceOrderId": invoiceOrderCode.substring(0, invoiceOrderCode.indexOf('@')),
        "supplierId": getSupplier.value,
        "discount": getInvoiceDiscount.value,
        "paid": getInvoicePaid.value,
        "note": getInvoiceNote.value,
        "details": detail
    }
}

function onStatusChange(element) {
    const value = $(element).val();
    const tagAll = $('#datatable tbody tr');
    const tag = $('tbody tr td span').filter(function () {
        return $(this).text().includes(value);
    });
    if (value === '') {
        tagAll.show();
    } else {
        tagAll.hide();
        tag.parent().parent().show();
    }
}

function onChangeQuantityOrPrice() {
    let quantityActual = document.getElementsByClassName('ip-edit-quantity-actual');
    let price = document.getElementsByClassName('ip-edit-price');
    let totalAmount = 0;
    if (quantityActual !== null && price !== null) {
        for (let i = 0; i < quantityActual.length; i++) {
            let quantityImport = Number(quantityActual[i].innerText)
            let priceImport = Number(price[i].innerText)
            totalAmount += (quantityImport * priceImport);
        }
    }
    let getDiscount = $('#ip-edit-invoice-discount').val();
    let getPaid = $('#ip-edit-invoice-paid').val();
    if (totalAmount < Number(getDiscount)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn số tiền NCC giảm. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    if (totalAmount < Number(getPaid)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn số tiền trả NCC. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    if (totalAmount < Number(getDiscount) + Number(getPaid)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn tổng tiền trả NCC và tiền NCC giảm. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    return true;
}