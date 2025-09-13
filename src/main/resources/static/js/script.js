document.addEventListener('DOMContentLoaded', function() {
    // Find all forms with the class 'confirm-delete'
    const deleteForms = document.querySelectorAll('.confirm-delete');

    deleteForms.forEach(form => {
        form.addEventListener('submit', function(event) {
            // Get the custom confirmation message from the data attribute
            const message = event.target.getAttribute('data-confirm-message') || 'Are you sure you want to delete this item?';

            // Display the confirmation dialog
            if (!confirm(message)) {
                // If the user clicks "Cancel", prevent the form from submitting
                event.preventDefault();
            }
        });
    });
});