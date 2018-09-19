//检查收货人
function checkReceiver(receiver) {
    var result = false;
    if (isNull(receiver)) {
        wx.showToast({title: '收货人不能为空'});
    } else if (receiver.length < 2) {
        wx.showToast({ title: '姓名至少包含两个中文字符' });
    } else if (!isChinese(receiver)) {
        wx.showToast({ title: '请输入身份证对应的真实姓名' });
    } else if (receiver.indexOf("先生") > -1 || receiver.indexOf("小姐") > -1 || receiver.indexOf("女士") > -1) {
        wx.showToast({ title: '请输入身份证对应的真实姓名' });
    } else {
        result = true;
    }
    return result;
}

//检查是否为中文
function isChinese(str) {
    return /^[\u4e00-\u9fa5]+$/.test(str);
}

//检查身份证号码
function checkCardId(num) {
    num = num.toUpperCase();
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        //toast.show('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
        wx.showToast({ title: '请输入合法的身份证号码' });
        return false;
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len,
        re;
    len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);
        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            //toast.show('输入的身份证号里出生日期不对！');
            wx.showToast({ title: '请输入合法的身份证号码' });
            return false;
        } else {
            //将15位身份证转成18位
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0,
                i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return num;
        }
    }

    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);
        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        //解决new Date解析19870412天数错误问题
        if (arrSplit[2] == '1987' && arrSplit[3] == '04' && arrSplit[4] == '12') {
            bGoodDay = true;
        } else {
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        }
        if (!bGoodDay) {
            //toast.show('输入的身份证号里出生日期不对！');
            wx.showToast({ title: '请输入合法的身份证号码' });
            return false;
        } else {
            //检验18位身份证的校验码是否正确。
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0,
                i;
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                //toast.show('18位身份证的校验码不正确！应该为：' + valnum);
                wx.showToast({ title: '请输入合法的身份证号码' });
                return false;
            }
            return true;
        }
    }
    return false;
}

//检查手机号
function checkMobile(mobile) {
    var MOBILE = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$";
    var CM = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)"; //移动
    var CU = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^1709\\d{7}$)"; //联通
    var CT = "(^1(33|53|77|8[019])\\d{8}$)|(^1700\\d{7}$)"; //电信
    var patt0 = new RegExp(MOBILE);
    var patt1 = new RegExp(CM);
    var patt2 = new RegExp(CU);
    var patt3 = new RegExp(CT);
    var result = false;
    if (isNull(mobile)) {
        wx.showToast({ title: '手机号不能为空' });
    } else if (mobile.length != 11) {
        wx.showToast({ title: '手机号必须为11位数字' });
    } else if (!patt0.test(mobile) && !patt1.test(mobile) && !patt2.test(mobile) && !patt3.test(mobile)) {
        wx.showToast({ title: '请输入正确的手机号码' });
    } else {
        result = true;
    }
    return result;
}

//检查省市区地址
function checkAddress(address) {
    if (isNull(address)) {
        wx.showToast({ title: '请选择地区' });
        return false;
    } else {
        return true;
    }
}

//检查详细地址
function checkAddressDetail(addressDetail) {
    if (isNull(addressDetail)) {
        wx.showToast({ title: '请输入地址' });
        return false;
    } else {
        return true;
    }
}

function isNull(value) {
    return value == undefined || value.trim() == '';
}

module.exports = {
    checkReceiver: checkReceiver,
    checkCardId: checkCardId,
    checkMobile: checkMobile,
    checkAddress: checkAddress,
    checkAddressDetail: checkAddressDetail
}