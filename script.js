window.onload = function() {
    document.getElementById('preloader').style.display = 'none';
    document.getElementById('content').style.display = 'block';
};

function togglePreloader() {
    let preloader = document.getElementById('preloader');
    let content = document.getElementById('content');
    preloader.style.display = 'block';
    content.style.display = 'none';

    setTimeout(function() {
        preloader.style.display = 'none';
        content.style.display = 'block';
    }, 3000); // El preloader se muestra durante 3 segundos
}
