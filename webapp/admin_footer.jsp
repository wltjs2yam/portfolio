<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $.ajax({
            url: 'footer',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var footer = '회사명 : ' + data.scompany + ' | 사업자등록번호 : ' + data.scompany_number + ' | 대표자 : ' + data.sname + ' | 대표 전화번호 : ' + data.stel + ' | 통신판매업 신고번호 : ' + data.snumber1 +
                    ' | 부가통신 사업자번호' + data.snumber2 + ' | 주소 : ' + data.saddress + ' | 정보관리책임자명 : ' + data.sname2 + ' | 정보책임자 이메일 : ' + data.semail2
                $('#footer').html(footer).css('color', 'white').css('text-align', 'center');

            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    });
</script>
<footer class="main_copyright" id="footer">

</footer>