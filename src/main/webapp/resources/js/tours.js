document.addEventListener('DOMContentLoaded', function() {
    var elem = document.querySelector('.autocomplete');
    elem.dropdown = document.querySelector(".dropdown-trigger");
    var instances = M.Autocomplete.init(elem, {data:{"Turkey": null,"Greece": null}});
});
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.slider');
    var instances = M.Slider.init(elems,{indicators: false});
});
