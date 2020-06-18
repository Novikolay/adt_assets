$(document).ready(function () {
    console.log("1")
    $('#btnSubmit').onclick(updateResult);
    function updateResult() {
        console.log("2")
        $.ajax({
            type: 'get',
            url: [["/banners/main"]],
            // url: [["/banners/main/update"]],
            // success: function(data){
            //     <![CDATA[
            //     $('.update_banner').html(data);
            //     ]]>
            //},
        })
    }
})