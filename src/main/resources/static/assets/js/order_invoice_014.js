window.onload = function () {
    $('.modal').hide()
}

function onClickSubmitEditOrderInvoice(e) {
    let getOrderCode = document.getElementById('ip-edit-order-invoice-code');
    let orderCode;
    if (getOrderCode !== null && getOrderCode !== undefined) {
        orderCode = getOrderCode.value;
        if (orderCode === null || orderCode === undefined || orderCode === '') {
            toastDanger('Lỗi', 'Vui lòng nhập mã đặt hàng');
            return;
        }
        if (orderCode.length < 5 || orderCode.length > 50) {
            toastDanger('Lỗi', 'Mã đặt hàng từ 5 đến 50 ký tự');
            return;
        }
    }
    let getDate = document.getElementById('ip-edit-order-invoice-date');
    let orderDate;
    if (getDate !== null && getDate !== undefined) {
        orderDate = getDate.value;
        if (orderDate === null || orderDate === undefined || orderDate === '') {
            toastDanger('Lỗi', 'Vui lòng chọn ngày hẹn nhận');
            return;
        }
    }
    let getSupplier = document.getElementById('ip-edit-order-invoice-supplier');
    let idSupplier;
    if (getSupplier !== null && getSupplier !== undefined) {
        idSupplier = getSupplier.value;
        if (idSupplier === null || idSupplier === undefined) {
            toastDanger('Lỗi', 'Vui long chọn nhà cung cấp');
            return;
        }
    }
    let getNote = document.getElementById('ip-edit-order-invoice-note');
    let note;
    if (getNote !== null && getNote !== undefined) {
        note = getNote.value;
    }
    let lstDetails = [];
    let getTrProduct = document.getElementsByClassName('tr-order-edit-product');
    if (getTrProduct !== null && getTrProduct !== undefined) {
        for (const $tr of getTrProduct) {
            let idProduct = $tr.id;
            let quantity;
            let getQuantity = $tr.getElementsByClassName('edit-quantity-product');
            if (getQuantity !== null && getQuantity !== undefined) {
                quantity = getQuantity[0].value;
                if (quantity === null || quantity === undefined || !Number(quantity) || Number(quantity) < 1) {
                    // toastDanger('Lỗi', 'Vui lòng nhập số lượng đặt lớn hơn 0');
                    // return;
                } else {
                    lstDetails.push({
                        "productId": idProduct,
                        "quantity": quantity
                    })
                }
            }
        }
    }
    if (lstDetails.length === 0) {
        toastDanger('Lỗi', 'Vui lòng nhập số lượng ít nhất 1 sản phẩm');
        return;
    }
    let obj = {
        "id": e.dataset.id,
        "code": orderCode,
        "date": orderDate,
        "supplierId": idSupplier,
        "note": note,
        "details": lstDetails
    }
    console.log(obj)
    $.ajax({
        url: '/api/order-invoice',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            toastSuccess('Thành công', 'Đã cập nhật hoá đơn đặt hàng NCC');
            setTimeout(function () {
                location.reload();
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

function onClickSubmitAddOrderInvoice() {
    let getOrderCode = document.getElementById('ip-order-invoice-code');
    let orderCode;
    if (getOrderCode !== null && getOrderCode !== undefined) {
        orderCode = getOrderCode.value;
        if (orderCode === null || orderCode === undefined || orderCode === '') {
            toastDanger('Lỗi', 'Vui lòng nhập mã đặt hàng');
            return;
        }
        if (orderCode.length < 5 || orderCode.length > 50) {
            toastDanger('Lỗi', 'Mã đặt hàng từ 5 đến 50 ký tự');
            return;
        }
    }
    let getDate = document.getElementById('ip-order-invoice-date');
    let orderDate;
    if (getDate !== null && getDate !== undefined) {
        orderDate = getDate.value;
        if (orderDate === null || orderDate === undefined || orderDate === '') {
            toastDanger('Lỗi', 'Vui lòng chọn ngày hẹn nhận');
            return;
        }
    }
    let getSupplier = document.getElementById('ip-order-invoice-supplier');
    let idSupplier;
    if (getSupplier !== null && getSupplier !== undefined) {
        idSupplier = getSupplier.value;
        if (idSupplier === null || idSupplier === undefined) {
            toastDanger('Lỗi', 'Vui long chọn nhà cung cấp');
            return;
        }
    }
    let getNote = document.getElementById('ip-order-invoice-note');
    let note;
    if (getNote !== null && getNote !== undefined) {
        note = getNote.value;
    }
    let lstDetails = [];
    let getTrProduct = document.getElementsByClassName('tr-order-add-product');
    if (getTrProduct !== null && getTrProduct !== undefined) {
        for (const $tr of getTrProduct) {
            // let getCheckBox = $tr.getElementsByClassName('chk-add-product');
            // let flag = false;
            // if (getCheckBox !== null && getCheckBox !== undefined) {
            //     if (getCheckBox[0].checked) {
            //         flag = true;
            //     }
            // }
            // if (!flag) {
            //     continue;
            // }
            let idProduct = $tr.id;
            let quantity;
            let getQuantity = $tr.getElementsByClassName('quantity-product');
            if (getQuantity !== null && getQuantity !== undefined) {
                quantity = getQuantity[0].value;
                if (quantity === null || quantity === undefined || !Number(quantity) || Number(quantity) < 1) {
                    // toastDanger('Lỗi', 'Vui lòng nhập số lượng đặt lớn hơn 0');
                    // return;
                } else {
                    lstDetails.push({
                        "productId": idProduct,
                        "quantity": quantity
                    })
                }
            }
        }
    }
    if (lstDetails.length === 0) {
        toastDanger('Lỗi', 'Vui lòng nhập số lượng ít nhất 1 sản phẩm');
        return;
    }
    let obj = {
        "code": orderCode,
        "date": orderDate,
        "supplierId": idSupplier,
        "note": note,
        "details": lstDetails
    }
    $.ajax({
        url: '/api/order-invoice',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            toastSuccess('Thành công', 'Đã tạo mới hoá đơn đặt hàng NCC');
            setTimeout(function () {
                location.reload();
            }, 2000)
        },
        error: function (error) {
            if (error.responseJSON.vn === null || error.responseJSON.vn === undefined) {
                let message = error.responseJSON.message + '';
                message = message.substring(message.indexOf(':') + 1)
                console.log(message)
                toastDanger('Lỗi', message);
                return;
            }
            toastDanger('Lỗi', error.responseJSON.vn);
        }
    })
    console.log(obj);
}

function onClickCancelOrderInvoice(e) {
    let id = e.dataset.id
    let url = '/api/order-invoice/' + id;
    let cancel = false;
    if (e.innerHTML.includes('Huỷ')) {
        cancel = true;
    }
    if (cancel) {
        url += '?cancel=true';
    } else {
        url += '?cancel=false';
    }
    $.ajax({
        url: url,
        method: 'PUT',
        success: function (data) {
            if (cancel) {
                toastSuccess('Thành công', 'Đã huỷ hoá đơn đặt hàng NCC');
            } else {
                toastSuccess('Thành công', 'Đã thực hiện đặt lại hàng hoá từ NCC');
            }
            setTimeout(function () {
                location.reload();
            }, 2000)
        },
        error: function (error) {
            if (error.responseJSON.vn === null || error.responseJSON.vn === undefined) {
                let message = error.responseJSON.message + '';
                message = message.substring(message.indexOf(':') + 1)
                console.log(message)
                toastDanger('Lỗi', message);
                return;
            }
            toastDanger('Lỗi', error.responseJSON.vn);
        }
    })
}

function onClickEditOrderInvoice(e) {
    $.ajax({
        url: '/api/order-invoice/' + e.dataset.id,
        success: function (data) {
            let getCode = document.getElementById('ip-edit-order-invoice-code');
            if (getCode !== null && getCode !== undefined) {
                getCode.value = data.invoiceOrderCode;
            }
            let getDate = document.getElementById('ip-edit-order-invoice-date');
            if (getDate !== null && getDate !== undefined) {
                getDate.value = data.orderDelivery;
            }
            let getSupplier = document.getElementById('ip-edit-order-invoice-supplier');
            if (getSupplier !== null && getSupplier !== undefined) {
                $(getSupplier).val(data.invoiceSupplierId).change();
            }
            $('#ip-edit-order-invoice-note').val(data.note);
            $('.edit-quantity-product').val(0);
            for (const detail of data.details) {
                $('.' + detail.productId).val(detail.quantity)
                // let text = $('.' + detail.productId).parent().parent();
                // $('.' + detail.productId).parent().parent().html();
                // text += document.getElementById('bodyEditProductOrderInvoice').innerHTML;
                // $('#bodyEditProductOrderInvoice').html(text);
            }

            if (data.status === 'ON') {
                $('#btnSubmitEditInvoice').show();
                $('#btnSubmitReverseCancel').hide();
                $('#btnSubmitEditInvoice').attr("data-id", e.dataset.id)
            } else {
                $('#btnSubmitEditInvoice').hide();
                $('#btnSubmitReverseCancel').show();
                $('#btnSubmitReverseCancel').attr("data-id", e.dataset.id)
            }
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

function searchUser(e) {
    let getAddProduct = document.getElementsByClassName('tr-order-add-product')
    let getEditProduct = document.getElementsByClassName('tr-order-edit-product')
    searchByValue(e.value.toLowerCase().trim(), getAddProduct);
    searchByValue(e.value.toLowerCase().trim(), getEditProduct);
    // enableCheckBox();
}

function searchByValue(value, lstInfo) {
    console.log(value)
    if (lstInfo !== null) {
        $(lstInfo).each((index, tr) => {
            $(tr).find('label').each((i, label) => {
                let text = label.innerHTML;
                if (nonAccentVietnamese(text).includes(nonAccentVietnamese(value))) {
                    tr.hidden = false;
                    return false;
                } else tr.hidden = true;
            })
        })
    }
}

function onChangeQuantity(e) {
    let value = e.value;
    if (value === null || value === undefined || value === '' || !Number(value) || Number(value) < 0) {
        e.value = 0;
    } else if (Number(value) < Number(e.dataset.min)) {
        e.value = Number(e.dataset.min);
    } else {
        e.value = Number(value);
    }
}

function nonAccentVietnamese(str) {
    str = str.toLowerCase();
    // We can also use this instead of from line 11 to line 17
    str = str.replace(/\u00E0|\u00E1|\u1EA1|\u1EA3|\u00E3|\u00E2|\u1EA7|\u1EA5|\u1EAD|\u1EA9|\u1EAB|\u0103|\u1EB1|\u1EAF|\u1EB7|\u1EB3|\u1EB5/g, "a");
    str = str.replace(/\u00E8|\u00E9|\u1EB9|\u1EBB|\u1EBD|\u00EA|\u1EC1|\u1EBF|\u1EC7|\u1EC3|\u1EC5/g, "e");
    str = str.replace(/\u00EC|\u00ED|\u1ECB|\u1EC9|\u0129/g, "i");
    str = str.replace(/\u00F2|\u00F3|\u1ECD|\u1ECF|\u00F5|\u00F4|\u1ED3|\u1ED1|\u1ED9|\u1ED5|\u1ED7|\u01A1|\u1EDD|\u1EDB|\u1EE3|\u1EDF|\u1EE1/g, "o");
    str = str.replace(/\u00F9|\u00FA|\u1EE5|\u1EE7|\u0169|\u01B0|\u1EEB|\u1EE9|\u1EF1|\u1EED|\u1EEF/g, "u");
    str = str.replace(/\u1EF3|\u00FD|\u1EF5|\u1EF7|\u1EF9/g, "y");
    str = str.replace(/\u0111/g, "d");
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    // Some system encode vietnamese combining accent as individual utf-8 characters
    str = str.replace(/\u0300|\u0301|\u0303|\u0309|\u0323/g, ""); // Huyền sắc hỏi ngã nặng
    str = str.replace(/\u02C6|\u0306|\u031B/g, ""); // Â, Ê, Ă, Ơ, Ư
    return str;
}

// event filter table by status
function onStatusChange(element) {
    const value = $(element).val();
    const tagAll = $('#datatable tbody tr');
    const tag = $('tr td').filter(function () {
        return $(this).text() === value;
    });

    if (value === 'Tất cả') {
        tagAll.show();
    } else {
        tagAll.hide();
        tag.parent().show();
    }
}