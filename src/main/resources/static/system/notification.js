let t1 = null
window.onload = function () {
    t1 = $('#datatable').DataTable({
        lengthChange: !1,
        "ordering": false,


        // scrollY: "54vh",
        // scrollX: false,
        // scrollCollapse: true,

        info: false,
        columns: [
            {data: 'btn', className: "text-left"},
            {data: 'productName', className: "text-left"},
            {data: 'content', className: "text-left"},
            {data: 'time', className: "text-left"}
        ]
    });

    getNotification()
}

function getNotification(){
    $.ajax({
        url: '/api/notification',
        method: 'GET',
        success: function (datas) {
            console.log(datas)
            let rows = new Array();
            let notReads = new Array();
            for(data of datas){
                if(!data.read){
                    notReads.push(data.productId)
                }
                let row = {
                    btn: '<button class="btn btn-primary" id="'+data.productId+'">Chi tiết</button>',
                    productName: data.productName,
                    content: data.content,
                    time: data.time
                }
                rows.push(row)
            }
            t1.clear().draw();
            t1.rows.add(rows).draw();

            console.log(notReads)
            for(let notRead of notReads){
                document.getElementById(notRead).parentNode.parentNode.style.fontWeight = '900';
            }

        },
        error: function (error) {
            console.log(error)
        }
    })
}
