function pad(number) {
    if (number < 10) {
        return '0' + number;
    }
    return number;
}

function timeNow(date) {
    time = new Date();
    time.setTime(time.getTime() + time.getTimezoneOffset()*60*1000*(-2));
    date.value = time.getUTCFullYear() +
        '-' + pad(time.getUTCMonth() + 1) +
        '-' + pad(time.getUTCDate()) +
        ' ' + pad(time.getUTCHours()) +
        ':' + pad(time.getUTCMinutes()) +
        ':' + pad(time.getUTCSeconds()) +
        '.' + (time.getUTCMilliseconds() / 1000).toFixed(9).slice(2, 11);
}

function timeNow2(date) {
    time = new Date();
    time.setTime(time.getTime() + time.getTimezoneOffset()*60*1000*(-1));
    date.value = time.getUTCFullYear() +
        '-' + pad(time.getUTCMonth() + 1) +
        '-' + pad(time.getUTCDate()) +
        ' ' + pad(time.getUTCHours()) +
        ':' + pad(time.getUTCMinutes()) +
        ':' + pad(time.getUTCSeconds()) +
        '.' + (time.getUTCMilliseconds() / 1000).toFixed(9).slice(2, 11);
}