document.querySelector('.cta-button').addEventListener('click', () => {
    alert('Redirecting to order page...');
});

document.querySelectorAll('.product-card button').forEach(button => {
    button.addEventListener('click', () => {
        alert('Added to cart!');
    });
});
