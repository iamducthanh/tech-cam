let t = null
window.onload = function () {
    t = $('#datatable').DataTable({
        paging: false,
        lengthChange: !1,
        "columnDefs": [{
            "searchable": false,
            "orderable": false,
            "targets": [0, 1],
        }],
        buttons:    [{
            extend : 'copy',
            text : '<i class="fas fa-copy"></i>',
            titleAttr : 'Sao chép'
        },
            {
                extend : 'excel',
                text : '<i class="fas fa-file-excel"></i>',
                titleAttr : 'Excel'
            },
            {
                extend : 'pdfHtml5',
                orientation : 'landscape',
                pageSize : 'LEGAL',
                text : '<i class="fas fa-file-pdf"></i>',
                titleAttr : 'PDF'
            },
            {
                extend : 'colvis',
                text : '<i class="fas fa-filter"></i>',
                titleAttr : 'Hiển thị tuỳ chỉnh'
            }],

        scrollY: screen.height / 1.3  + 'px',
        scrollX: true,
        scrollCollapse: true,

        info: false,
        columns: [
            { data: 'stt', className: "text-center" },
            { data: 'action', className: "text-center" },
            { data: 'orderId', className: "text-center order-id" },
            { data: 'tax', className: "text-end order-tax" },
            { data: 'createDate', className: "text-center" },
            { data: 'type', className: "text-center" },
            { data: 'payment', className: "text-center" },
            { data: 'status', className: "text-center" },
        ]
    });

    t.buttons().container().appendTo("#datatable_wrapper .col-md-6:eq(0)"),
        $(".dataTables_length select").addClass("form-select form-select-sm");
    $("body").find(".dataTables_scrollBody").addClass("scrollbar");

    t.on( 'order.dt search.dt', function () {
        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();

    connect();

}

function onAddRow(){
    let dataOrderNew = new Array();
    let orderNew = {};
    orderNew.stt = 1;
    orderNew.action = '<i class="col-4 fas fa-info-circle fs-2" style="color: #655be6;" title="Thông tin" onclick="onBtnInfo(this)"></i>';
    orderNew.orderId = '<p style="font-weight: 900;">Đơn hàng mới</p>';
    orderNew.tax = '';
    orderNew.createDate = '';
    orderNew.type = '';
    orderNew.payment = '';
    orderNew.status = '';

    dataOrderNew.push(orderNew);
    t.rows.add(dataOrderNew).draw(false);
}

var stompClient = null;

function connect(event) {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/notify', onNotifyReceived);
}

function onError(error) {
    console.log(error)
}

function onNotifyReceived(payload) {
    console.log('đã nhân đc')
    toastInfo('Thông báo', 'Bạn vừa nhận được một đơn hàng mới!');
    onAddRow();
    var order = JSON.parse(payload.body);
    console.log(order)
}



