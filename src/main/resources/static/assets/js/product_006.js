let dataImageUploadEdit = [];
let dataImageUploadAdd = [];
let sizeImage = 0;

window.onload = function () {
    $('.modal').hide();
    CKEDITOR.replace('productAddDescription');
    CKEDITOR.replace('productEditDescription');
    $('#ip-edit-product-image').change(function () {
        for (var i = 0, f; (f = this.files[i]); i++) {
            if (!f.type.match("image.*")) {
                continue;
            }
            var reader = new FileReader();
            reader.onload = (function (theFile) {
                return function (e) {
                    dataImageUploadEdit.push(e.target.result)
                    addImagePreview(e.target.result)
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
    })
    $('#ip-add-product-image').change(function () {
        for (var i = 0, f; (f = this.files[i]); i++) {
            if (!f.type.match("image.*")) {
                continue;
            }
            var reader = new FileReader();
            reader.onload = (function (theFile) {
                return function (e) {
                    dataImageUploadAdd.push(e.target.result)
                    addImagePreviewAdd(e.target.result)
                };
            })(f);
            // Read in the image file as a data URL.
            reader.readAsDataURL(f);
        }
    })
    showAttributeCategory();
}

function showAttributeCategory() {
    let category = document.getElementById('ip-add-product-category');
    if (category !== null && category !== undefined) onChangeCategory(category);
}

function zoomImageProduct(e) {
    let srcImg = e.src
    console.log(srcImg)
    $('#modal-zoom-image').modal('show');
    let imgZoomImage = document.getElementById('img-zoom-image')
    if (imgZoomImage !== null && imgZoomImage !== undefined) {
        imgZoomImage.src = srcImg
    }
}

function onClickChooseImage() {
    $('#ip-edit-product-image').click()
}

function onClickChooseImageAdd() {
    $('#ip-add-product-image').click()
}

function addImagePreviewAdd(image) {
    let carouselIndicators = document.getElementById('carousel-indicators-add')
    if (sizeImage === 0) {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
    } else {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '"></button>'
    }
    let divImage = document.createElement('div')
    divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
    divImage.innerHTML = [
        '<img style="height: 600px;" src="',
        image,
        '" title="',
        '"/>'
    ].join("");
    document.getElementById("previewUploadAdd").insertBefore(divImage, null);
    sizeImage += 1;
}

function addImagePreview(image) {
    let carouselIndicators = document.getElementById('carousel-indicators')
    if (sizeImage === 0) {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
    } else {
        carouselIndicators.innerHTML +=
            '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
            'aria-label="Slide ' + sizeImage + '"></button>'
    }
    let divImage = document.createElement('div')
    divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
    divImage.innerHTML = [
        '<img style="height: 600px;" src="',
        image,
        '" title="',
        '"/>'
    ].join("");
    document.getElementById("previewUpload").insertBefore(divImage, null);
    sizeImage += 1;
}

function onClickUpdateProduct(e) {
    // get element mã sản phẩm
    let getElementProductCode = document.getElementById('ip-edit-product-code');
    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-edit-product-name');
    // get element giá bán
    let getElementProductPrice = document.getElementById('ip-edit-product-price');
    // get element thương hiệu
    let getElementProduuctBrand = document.getElementById('ip-edit-product-brand');
    // get element trạng thái kinh doanh
    let getElementProductStatus = document.getElementById('ip-edit-product-status');
    // get element danh mục sản phẩm
    let getElementProductCategory = document.getElementById('ip-edit-product-category');
    // get element thuộc tính sản phẩm
    let getElementProductProperty = document.getElementById('product-edit-property');
    // get mô tả chi tiết sản phẩm
    let productDescription = CKEDITOR.instances['productEditDescription'].getData();
    let objectProduct = validateObjectProduct(
        getElementProductCode,
        getElementProductName,
        getElementProductPrice,
        getElementProduuctBrand,
        getElementProductStatus,
        getElementProductCategory,
        productDescription,
        dataImageUploadEdit,
        getElementProductProperty,
    );
    console.log(objectProduct);
    if (objectProduct !== null && objectProduct !== undefined) {
        objectProduct["productId"] = e.dataset.id
        $.ajax({
            url: '/api/product',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(objectProduct),
            success: function (data) {
                console.log(data);
                toastSuccess('Thành công', 'Đã cập nhật sản phẩm')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function onClickSaveProduct() {
    // get element mã sản phẩm
    let getElementProductCode = document.getElementById('ip-add-product-code');
    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-add-product-name');
    // get element giá bán
    let getElementProductPrice = document.getElementById('ip-add-product-price');
    // get element thương hiệu
    let getElementProduuctBrand = document.getElementById('ip-add-product-brand');
    // get element trạng thái kinh doanh
    let getElementProductStatus = document.getElementById('ip-add-product-status');
    // get element danh mục sản phẩm
    let getElementProductCategory = document.getElementById('ip-add-product-category');
    // get element thuộc tính sản phẩm
    let getElementProductProperty = document.getElementById('product-add-property');
    // get mô tả chi tiết sản phẩm
    let productDescription = CKEDITOR.instances['productAddDescription'].getData();
    let objectProduct = validateObjectProduct(
        getElementProductCode,
        getElementProductName,
        getElementProductPrice,
        getElementProduuctBrand,
        getElementProductStatus,
        getElementProductCategory,
        productDescription,
        dataImageUploadAdd,
        getElementProductProperty,
    );
    console.log(objectProduct);
    if (objectProduct !== null && objectProduct !== undefined) {
        $.ajax({
            url: '/api/product',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(objectProduct),
            success: function (data) {
                toastSuccess('Thành công', 'Đã lưu mới sản phẩm')
                console.log(data);
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function validateObjectProduct(
    getElementProductCode,
    getElementProductName,
    getElementProductPrice,
    getElementProduuctBrand,
    getElementProductStatus,
    getElementProductCategory,
    productDescription,
    productImages,
    getElementProductProperty,
) {
    let productCode, productName, productPrice, productBrand,
        productStatus, productCategory, productProperties;
    // get mã sản phẩm
    if (getElementProductCode !== null && getElementProductCode !== undefined) {
        productCode = getElementProductCode.value;
    }
    // get tên sản phẩm
    if (getElementProductName !== null && getElementProductName !== undefined) {
        productName = getElementProductName.value;
    }
    // get giá sản bán
    if (getElementProductPrice !== null && getElementProductPrice !== undefined) {
        productPrice = getElementProductPrice.value;
    }
    // get thương hiệu
    if (getElementProduuctBrand !== null && getElementProduuctBrand !== undefined) {
        productBrand = getElementProduuctBrand.value;
    }
    // get trạng thái kinh doanh
    if (getElementProductStatus !== null && getElementProductStatus !== undefined) {
        productStatus = getElementProductStatus.checked;
    }
    // get loại sản phẩm
    if (getElementProductCategory !== null && getElementProductCategory !== undefined) {
        productCategory = getElementProductCategory.value;
    }

    // get thuộc tính sản phẩm
    if (getElementProductProperty !== null && getElementProductProperty !== undefined) {
        productProperties = [];
        let getElementInput = getElementProductProperty.getElementsByTagName('input');
        let getElementSelect = getElementProductProperty.getElementsByTagName('select');
        if (getElementInput !== null && getElementInput !== undefined) {
            for (let i = 0; i < getElementInput.length; i++) {
                let value = getElementInput[i].value;
                if (value !== null && value !== undefined && value !== '') {
                    if (value.length > 50) {
                        console.log('Giá trị thuộc tính không được quá 50 ký tự.');
                        return;
                    }
                    productProperties.push({
                        "propertyId": getElementInput[i].dataset.id,
                        "fixedValueId": null,
                        "inputValue": value,
                    })
                }
            }
        }
        if (getElementSelect !== null && getElementSelect !== undefined) {
            for (let i = 0; i < getElementSelect.length; i++) {
                let value = getElementInput[i].value;
                if (value !== null && value !== undefined && value !== '') {
                    productProperties.push({
                        "propertyId": getElementSelect[i].dataset.id,
                        "fixedValueId": value,
                        "inputValue": null,
                    })
                }
            }
        }
    }
    // get mô tả chi tiết sản phẩm
    if (productDescription !== null && productDescription !== undefined) {
        productDescription = (productDescription + '').substring(3);
        productDescription = (productDescription + '').substring(0, productDescription.length - 5);
    }

    // validate mã sản phẩm
    if (productCode === null || productCode === undefined || productCode === '') {
        toastDanger('Lỗi', 'Vui lòng nhập mã sản phẩm');
        return;
    }
    if (!/^[a-zA-Z0-9\-]+$/.test(productCode)) {
        console.log('Mã sản phẩm không đúng');
        toastDanger('Lỗi', 'Mã sản phẩm không đúng');
        return;
    }
    if (productCode.length < 10 || productCode.length > 50) {
        console.log('Mã sản phẩm chỉ từ 10 đến 50 ký tự');
        toastDanger('Lỗi', 'Mã sản phẩm chỉ từ 10 đến 50 ký tự');
        return;
    }

    // validate tên sản phẩm
    if (productName === null || productName === undefined || productName === '') {
        console.log('Vui lòng nhập tên sản phẩm');
        toastDanger('Lỗi', 'Vui lòng nhập tên sản phẩm');
        return;
    }
    if (productCode.length < 10 || productCode.length > 100) {
        console.log('Tên sản phẩm chỉ từ 10 đến 100 ký tự');
        toastDanger('Lỗi', 'Tên sản phẩm chỉ từ 10 đến 100 ký tự');
        return;
    }

    // validate giá bán
    if (productPrice === null || productPrice === undefined || productPrice === '') {
        console.log('Vui lòng nhập giá bán đề xuất');
        toastDanger('Lỗi', 'Vui lòng nhập giá bán đề xuất');
        return;
    }
    if (!/^[0-9]+$/.test(productPrice)) {
        console.log('Giá bán đề xuất không đúng');
        toastDanger('Lỗi', 'Giá bán đề xuất không đúng');
        return;
    }
    if (Number(productPrice) < 1) {
        console.log('Giá bán đề xuất phải từ 1đ');
        toastDanger('Lỗi', 'Giá bán đề xuất phải từ 1đ');
        return;
    }

    // validate thương hiệu
    if (productBrand === null || productBrand === undefined || productBrand === '') {
        console.log('Vui lòng chọn thương hiệu sản xuất của sản phẩm');
        toastDanger('Lỗi', 'Vui lòng chọn thương hiệu của sản phẩm');
        return;
    }

    // validate trạng thái kinh doanh
    if (productStatus === null || productStatus === undefined || productStatus === '') {
        productStatus = false;
    }

    // validate loại sản phẩm & thuộc tính sản phẩm
    if (productCategory === null || productCategory === undefined || productCategory === '') {
        console.log('Vui lòng chọn danh mục sản phẩm');
        toastDanger('Lỗi', 'Vui lòng chọn danh mục sản phẩm');
        return;
    }

    // validate mô tả chi tiết sản phẩm
    if (productDescription === null || productDescription === undefined || productDescription === '') {
        console.log('Vui lòng nhập mô tả chi tiết sản phẩm');
        toastDanger('Lỗi', 'Vui lòng nhập mô tả chi tiết sản phẩm');
        return;
    }

    // validate hình ảnh sản phẩm
    if (productImages === null || productImages === undefined || productImages.length === 0) {
        console.log('Vui lòng chọn hình ảnh sản phẩm');
        toastDanger('Lỗi', 'Vui lòng chọn hình ảnh sản phẩm');
        return;
    }

    return {
        "productCode": productCode,
        "productName": productName,
        "productPrice": productPrice,
        "productBrand": productBrand,
        "productStatus": productStatus,
        "productCategory": productCategory,
        "productDescription": productDescription,
        "productImages": productImages,
        "productProperties": productProperties,
    };
}

function onChangeCategory(e) {
    // TODO hiển thị danh sách thuộc tính sản phẩm
    let categoryId = e.value
    let status = e.dataset.status
    let url;
    if (status === 'edit') {
        url = '/product/property/edit?category=' + categoryId + '&product=' + e.dataset.id
    } else {
        url = '/product/property/add?category=' + categoryId
    }
    $.ajax({
        url: url,
        method: 'GET',
        success: function (html) {
            let getElementProductProperty = document.getElementById('product-' + status + '-property');
            if (getElementProductProperty !== null) {
                getElementProductProperty.innerHTML = html;
            }
        }
    })
}

function onClickAddProduct() {
    dataImageUploadEdit = [];
}

function onClickEditProduct(e) {
    dataImageUploadEdit = [];
    let id = e.dataset.id;
    // get element mã sản phẩm
    let getElementButtonEditProduct = document.getElementById('btn-submit-edit-product');
    if (getElementButtonEditProduct !== null) {
        getElementButtonEditProduct.dataset.id = id
    }
    // get element mã sản phẩm
    let getElementProductCode = document.getElementById('ip-edit-product-code');
    // get element tên sản phẩm
    let getElementProductName = document.getElementById('ip-edit-product-name');
    // get element giá bán
    let getElementProductPrice = document.getElementById('ip-edit-product-price');
    // get element thương hiệu
    let getElementProduuctBrand = document.getElementById('ip-edit-product-brand');
    // get element trạng thái kinh doanh
    let getElementProductStatus = document.getElementById('ip-edit-product-status');
    // get element danh mục sản phẩm
    let getElementProductCategory = document.getElementById('ip-edit-product-category');
    // get element thuộc tính sản phẩm
    let getElementProductProperty = document.getElementById('product-edit-property');
    $.ajax({
        url: '/api/product/' + id,
        method: 'GET',
        success: function (data) {
            console.log(data)
            if (getElementProductCode !== null) {
                getElementProductCode.value = data.productCode;
            }
            if (getElementProductName !== null) {
                getElementProductName.value = data.productName;
            }
            if (getElementProductPrice !== null) {
                getElementProductPrice.value = data.productPrice;
            }
            if (getElementProduuctBrand !== null) {
                getElementProduuctBrand.value = data.productBrand;
            }
            if (getElementProductStatus !== null) {
                getElementProductStatus.checked = data.productStatus === 'ON';
            }
            if (data.lstImages !== null && data.lstImages !== undefined) {
                dataImageUploadEdit = data.lstImages
                sizeImage = 0;
                let previewUpload = document.getElementById('previewUpload')
                let carouselIndicators = document.getElementById('carousel-indicators')
                if (previewUpload !== null && previewUpload !== undefined) {
                    previewUpload.innerHTML = ''
                    carouselIndicators.innerHTML = ''
                }
                for (let i = 0; i < data.lstImages.length; i++) {
                    addImagePreview(data.lstImages[i])
                }
            }
            CKEDITOR.instances['productEditDescription'].setData(data.productDescription)
            if (getElementProductCategory !== null) {
                getElementProductCategory.value = data.productCategory;
                getElementProductCategory.dataset.id = id
                $.ajax({
                    url: '/product/property/edit?category=' + data.productCategory + '&product=' + id,
                    method: 'GET',
                    success: function (html) {
                        if (getElementProductProperty !== null) {
                            getElementProductProperty.innerHTML = html;
                        }
                    }
                })
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR)
        }
    })
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