$(function() {
    "use strict";

    $('#fullpage').fullpage({
        sectionsColor: [
            '#000',
            '#599E48',
            '#573413',
            '#599E48',
            '#573413',
            '#599E48',
            '#573413',
            '#000'
        ],
        anchors: [
            'start-page',
            'first-page',
            'second-page',
            'third-page',
            'fourth-page',
            'fifth-page',
            'sixth-page',
            'end-page'
        ],
        loopHorizontal: false
    });

    // Video
    var $vid = $('#demo-vid');
    var $arrow = $('.fp-controlArrow');
    $vid.on('playing', function() {
        $arrow.css('display', 'none');
    });
    $vid.on('ended pause', function() {
        $arrow.css('display', 'block');
    });

    // Bottom images && top titles
    var $sections = $('.section');
    $sections.each(function(i, section) {
        var $slides = $(section).find('.slide');
        var width = 100 / $slides.length + '%';
        $slides.each(function(j, slide) {
            $(slide).find('.image-bottom-wrapper').each(function(k, wrapper) {
                $(wrapper).css('width', width);
            });
            $(slide).find('.topTitle').each(function(l, title) {
                $(title).css('width', width);
            });
            $(slide).find('.overview').each(function(m, overview) {
                $(overview).css('width', width);
            });
        });
    });

    window.setTimeout(function() {
        setOverviewAndImage();
    }, 1000);
});

// Overview
function setOverviewAndImage() {
    var windowHeight = window.innerHeight;
    $('.overview').each(function(i, overview) {
        var height = $(overview).closest('.section').find('h1').first().height();
        $(overview).css('height', windowHeight - height);
    });
    $('.image-bottom').each(function(i, image) {
        var height = $(image).closest('.section').find('h1').first().height();
        console.log(windowHeight);
        console.log(height);
        console.log(windowHeight - height);
        $(image).css('height', (windowHeight - height));
    });
}