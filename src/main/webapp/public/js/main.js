// Ẩn hiện modal
const modal = document.querySelector('.js-modal');
const cardModal = document.querySelector('.card-modal');
const modalCloses = document.querySelectorAll('.js-modal-close');
const modalContainers = document.querySelectorAll('.js-modal-container');

// Hàm hiển thị modal
function showModal () {
    modal.classList.add('open');
}

// Hàm ẩn modal
function hideModal () {
    modal.classList.remove('open');
    cardModal.classList.remove('open');
}

// Close
modalCloses.forEach(modalClose => {
    modalClose.addEventListener('click', hideModal);
})

// Đóng khi click ra ngoài form
modal.addEventListener('click', hideModal);
// cardModal.addEventListener('click', hideModal);

modalContainers.forEach(modalContainer => {
    modalContainer.addEventListener('click', function (event) {
        event.stopPropagation(); // Chặn hiện tượng nổi bọt
    })
})

// Thêm card
const addCardBtns = document.querySelectorAll(".add-card-btn");
addCardBtns.forEach((addCardBtn, index) => {
    addCardBtn.addEventListener('click', (event) => {
        const parent = event.target.parentElement;
        const input = parent.querySelector('.input-add-card');
        input.style.display = "block";
        input.focus();
        event.target.style.display = "none";
    })
});

const addCardInputs = document.querySelectorAll(".input-add-card");
addCardInputs.forEach((addCardInput, index) => {
    addCardInput.addEventListener('blur', (event) => {
        const parent = event.target.parentElement;
        const btn = parent.querySelector('.add-card-btn');
        btn.style.display = "block";
        event.target.style.display = "none";
    })
    addCardInput.addEventListener('keyup', (event) => {
        if (event.keyCode == 13) {
            event.target.parentElement.submit();
        }
    })
});

// Sửa hoặc xóa card
const listItems = document.querySelectorAll(".list-item");
listItems.forEach((listItem, index) => {
    listItem.addEventListener('click', (event) => {
        cardModal.classList.add('open');
        const input = cardModal.querySelector("#edit-card");
        input.value = event.target.textContent;
        input.focus();
    })
});


