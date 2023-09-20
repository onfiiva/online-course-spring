document.addEventListener("DOMContentLoaded", function () {
    var currentPage = window.location.pathname; // Получаем текущий URL страницы
    var pagesList = document.querySelectorAll("._pages_list"); // Находим все элементы ul

    pagesList.forEach(function (page) {
        // Проверяем, соответствует ли атрибут href текущей странице
        if (page.getAttribute("th:href") === currentPage) {
            page.classList.add("active"); // Добавляем класс active
        }
    });
});
