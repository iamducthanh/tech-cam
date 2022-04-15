
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
    loadNotifyHead();
    console.log('đã nhân đc')
    toastInfo('Thông báo', 'Bạn vừa nhận được một đơn hàng mới!');
    var order = JSON.parse(payload.body);
    console.log(order)
    let href = window.location.href;
    if(href.indexOf('order') > -1){
        onAddRow(order);
    }

}



