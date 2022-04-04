let t = null
window.onload = function () {
    t = $('#datatable').DataTable({
        lengthChange: !1,
        "columnDefs": [{
            "searchable": false,
            "orderable": false,
            "targets": [0, 1],
        }],
        buttons: [{
            extend: 'copy',
            text: '<i class="fas fa-copy"></i>',
            titleAttr: 'Sao chép'
        },
            {
                extend: 'excel',
                text: '<i class="fas fa-file-excel"></i>',
                titleAttr: 'Excel'
            },
            {
                extend: 'pdfHtml5',
                orientation: 'landscape',
                pageSize: 'LEGAL',
                text: '<i class="fas fa-file-pdf"></i>',
                titleAttr: 'PDF'
            },
            {
                extend: 'colvis',
                text: '<i class="fas fa-filter"></i>',
                titleAttr: 'Hiển thị tuỳ chỉnh'
            }],

        scrollY: "54vh",
        scrollX: true,
        scrollCollapse: true,

        info: false,
        columns: [
            {data: 'stt', className: "text-center"},
            {data: 'action', className: "text-center"},
            {data: 'orderId', className: "text-center order-id"},
            {data: 'tax', className: "text-end order-tax"},
            {data: 'createDate', className: "text-center"},
            {data: 'type', className: "text-center"},
            {data: 'payment', className: "text-center"},
        ]
    });

    t.buttons().container().appendTo("#datatable_wrapper .col-md-6:eq(0)"),
        $(".dataTables_length select").addClass("form-select form-select-sm");
    $("body").find(".dataTables_scrollBody").addClass("scrollbar");

    t.on('order.dt search.dt', function () {
        t.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();

    connect();

}

function onAddRow(order) {
    let dataOrderNew = new Array();
    let orderNew = {};
    let status = order.transactionStatus;
    if (status === 'CANCEL') {
        orderNew.action = '<button class="status badge btn btn-secondary fs-6 waves-effect waves-light" onclick="onBtnInfo(this)">Huỷ</button>';
    } else if (status === 'VERIFY') {
        orderNew.action = '<button class="status badge btn btn-warning fs-6 waves-effect waves-light" onclick="onBtnInfo(this)" onmouseover="$(this).text(\'Xác nhận\')" onmouseleave="$(this).text(\'Chờ xác nhận\')">Chờ xác nhận</button>';
    } else if (status === 'CONFIRM') {
        orderNew.action = '<button class="status badge btn btn-info fs-6 waves-effect waves-light" onclick="onBtnInfo(this)" onmouseover="$(this).text(\'Xuất hàng\')" onmouseleave="$(this).text(\'Chờ xuất hàng\')">Chờ xuất hàng</button>';
    } else if (status === 'SHIPPING') {
        orderNew.action = '<button class="status badge btn btn-primary fs-6 waves-effect waves-light" onclick="onBtnInfo(this)">Đang giao hàng</button>';
    } else if (status === 'DONE') {
        orderNew.action = '<button class="status badge btn btn-success fs-6 waves-effect waves-light" onclick="onBtnInfo(this)">Hoàn thành</button>';
    }

    if (order.orderType === 'ONLINE') {
        orderNew.type = '<p style="font-weight: 700">' + 'Online' + '</p>';
    } else if (order.orderType === 'COUNTER') {
        orderNew.type = '<p style="font-weight: 700">' + 'Tại quầy' + '</p>';
    }

    if (order.paymentMethod === 'PAYMENT') {
        orderNew.payment = '<p style="font-weight: 700">' + 'Thẻ' + '</p>';
    } else if (order.paymentMethod === 'CASH') {
        orderNew.payment = '<p style="font-weight: 700">' + 'Tiền mặt' + '</p>';
    }

    orderNew.stt = 1;
    orderNew.orderId = '<p style="font-weight: 700">' + 'HD00' + order.id + '</p>';
    orderNew.tax = '<p style="font-weight: 700">' + order.tax.toLocaleString('vi-VN') + 'đ' + '</p>';
    orderNew.createDate = '<p style="font-weight: 700">' + moment(order.orderDate).format('DD/MM/YYYY hh:mm:ss') + '</p>';

    console.log(orderNew)

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
    var order = JSON.parse(payload.body);
    console.log(order)
    onAddRow(order);
}



