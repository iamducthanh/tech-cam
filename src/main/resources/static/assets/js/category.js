function onAdd(elm){
    let level = elm.getAttribute('level');
    let parentId = elm.getAttribute('parentId');
    console.log(level)
    console.log(parentId)
    $('#error')[0].style.display = 'none'
    onModalCategory(null, level, parentId)
    $('#modalTitle')[0].innerHTML = "Thêm tiêu đề";

}

function onEdit(elm){
    let level = elm.getAttribute('level');
    let categoryId = elm.getAttribute("categoryId")
    let parentId = elm.getAttribute('parentId');
    $('#modalTitle')[0].innerHTML = "Sửa tiêu đề";
    onModalCategory(categoryId, level, parentId)
}

function onRemove(){

}

function onModalCategory(categoryId, level, parentId){
    $('#editCategory')[0].style.display = 'unset';
    $('#parentId')[0].value = parentId;
    $('#levelCategory')[0].value = level;
    $('#categoryId')[0].value = categoryId;
}
function closeModalCategory(){
    $('#editCategory')[0].style.display = 'none';
}

function saveCategory(){
    let parentId = $('#parentId')[0].value
    let level = $('#levelCategory')[0].value
    let categoryName = $('#categoryName')[0].value

    console.log(parentId)
    console.log(level)
    console.log(categoryName)

    $.ajax({
        url: '/category',
        method: 'POST',
        data: JSON.stringify({
            categoryId: '',
            categoryName: categoryName,
            parentId: parentId
        }),
        contentType: 'application/json',
        success: function (datas) {
            console.log(datas)
            $('#error')[0].style.display = 'none'
            // toastSuccess("Thành công", "Thêm tiêu đề thành công.")
            window.location.reload();
        },
        error: function (error) {
            $('#error')[0].style.display = 'unset'
            $('#error')[0].innerHTML = error.responseJSON.vn
            console.log(error)
        }
    })
}