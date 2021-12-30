var main = {
// a.js도 a.js만의 init과 save function을 가지고 있다.
/*
브라우저 스코프는 공용 곤간으로 쓰이기 때문에 나중에 로딩된 js의 init,save가 먼저 로딩된 js의 function을 덮어쓰게 됩니다.
여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생할 수 있습니다.
그러다 보니 이런 문제를 피하려고 index.js만의 유효범위를 만들어 사용합니다.
*/
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-update').on('click', function(){
            _this.update();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        })
    },
    save : function (){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 메인으로 돌아감
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();