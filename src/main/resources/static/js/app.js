var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token)
});

function removeCase(caseNum) {
    $.ajax({
        type: "post",
        url: "../case/remove/" + caseNum,
        data: JSON.stringify(caseNum),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.code === 0) {
                alert("删除成功！");
                window.location.reload();
            } else {
                alert(result.code + result);
            }
        },
        error: function (r) {
            alert("error" + r);
        }
    })
}

function addDirectory(caseNum) {
    var directoryName = $("#directoryName").val();
    if ($.isEmptyObject(directoryName)) {
        alert("目录不能为空！");
        return;
    }
    $.ajax({
        type: "post",
        url: "../dossier/addDirectory",
        data: JSON.stringify({caseNum : caseNum, directoryName : directoryName}),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.code === 0) {
                alert("添加成功！");
                window.location.reload();
            } else {
                alert(result.code + result);
            }
        },
        error: function (r) {
            alert("error" + r);
        }
    })
}

function updateCurrentDossier(dossierId) {
    // 装载局部刷新返回的页面
    $('#div-media-container').load('/dossier/updateCurrentDossier/' + dossierId);
}

// function updateHisList() {
//     var recordQueryCondition = [[${recordQueryCondition}]];
//     $.ajax({
//         url: '../user/his',
//         type: 'post',
//         dataType: 'json',
//         data: recordQueryCondition,
//         cache: false,
//         async: true,
//         success: function (data) {
//             $('user-his-div').load(data);
//         }
//     });
// }
