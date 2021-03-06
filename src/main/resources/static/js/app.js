var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token)
});

function removeCase(caseNum) {
    if (confirm("操作不可逆，案件所含卷宗也会被删除，确认删除案件：#" + caseNum + "?")) {
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
    $('#div-media-content').load('/dossier/common/updateCurrentDossier/' + dossierId);
}

