$(document).ready(function() {
    $('button[name=upload-submit]').click(function(e) {
        e.preventDefault();
        var id = $(this).attr('button');
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();
        $('#mask').css({'width':maskWidth,'height':maskHeight});
        $('#mask').fadeIn(1000);
        $('#mask').fadeTo("slow",0.8);
        var winH = $(window).height();
        var winW = $(window).width();
        $(id).css('top',  winH/2-$(id).height()/2);
        $(id).css('left', winW/2-$(id).width()/2);
        $(id).fadeIn(2000);
    });
    $('.window .close').click(function (e) {
        e.preventDefault();
        $('#mask, .window').hide();
    });
    $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
    });
});

// var f = document.querySelector('form')
//
// f.addEventListener('submit', function(e) {
//     e.preventDefault();
//     var data = new FormData(this) // Сборка формы
//     var url = 'https://jsonplaceholder.typicode.com/posts'
//     fetch(url, {
//         method: 'post',
//         headers: {
//             "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
//         },
//         body: data // Отправка самой формы
//     })
//         .then(response => response.json())
//         .then((json) => { // Ответ
//             if (json.id === 101) { // Для примера проверка пройдена если id === 101
//                 // Добавление поля
//                 var info = document.querySelector('.info')
//                 info.innerText = 'Удачно Отправлено'
//             }
//             // Дебаг узнать что прошла форма
//             console.log(json)
//         })
//         .catch(err => console.log(err));
// })