// Ẩn hiện modal
const modals = document.querySelectorAll('.js-modal');
const modal = document.querySelector('.modal');
const cardModal = document.querySelector('.card-modal');
const listModal = document.querySelector('.list-modal');
const boardModal = document.querySelector('.board-modal');

const modalCloses = document.querySelectorAll('.js-modal-close');
const modalContainers = document.querySelectorAll('.js-modal-container');

// Hàm hiển thị modal
function showModal () {
    modal.classList.add('open');
}

// Hàm hiển thị card-modal
function showCardModal () {
    cardModal.classList.add('open');
}

// Hàm hiển thị list-modal
function showListModal () {
    listModal.classList.add('open');
}

// Hàm hiển thị board-modal
function showBoardModal () {
    boardModal.classList.add('open');
}

// Hàm ẩn modal
function hideModal () {
    modals.forEach(modal => modal.classList.remove('open'));
}

// Close
modalCloses.forEach(modalClose => {
    modalClose.addEventListener('click', hideModal);
})

// Đóng khi click ra ngoài form
modals.forEach(modal => modal.addEventListener('click', hideModal));

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
const cards = document.querySelectorAll(".list-item");
cards.forEach((card, index) => {
    card.addEventListener('click', (event) => {
        showCardModal();
        const parent = event.target.parentElement;
        const listId = parent.dataset['index'];
        const cardId = event.target.dataset['index'];
        const  boardId = parent.parentElement.dataset['index'];
        console.log(boardId, listId, cardId);
        cardModal.querySelector("#board-id").value = boardId;
        cardModal.querySelector("#list-id").value = listId;
        cardModal.querySelector("#card-id").value = cardId;
        const input = cardModal.querySelector("#edit-card");
        input.value = event.target.textContent;
        input.focus();
    })
});

// Sửa hoặc xóa list
const listItems = document.querySelectorAll(".list-title");
listItems.forEach((listItem, index) => {
    listItem.addEventListener('click', (event) => {
        showListModal();
        const parent = listItem.parentElement;
        const listId = listItem.dataset['index'];
        const  boardId = parent.dataset['index'];
        console.log(boardId, listId);
        listModal.querySelector(".board-id").value = boardId;
        listModal.querySelector(".list-id").value = listId;
        const input = listModal.querySelector("#edit-list");
        input.value = listItem.textContent;
        input.focus();
    })
});

// Sửa hoặc xóa board
const boardEditBtns = document.querySelectorAll(".board-edit-btn");
boardEditBtns.forEach((editBtn, index) => {
    editBtn.addEventListener('click', (event) => {
        event.preventDefault();
        boardModal.querySelector("#board-id").value = editBtn.dataset.index;
        const boardContainer = editBtn.parentElement;
        const boardName = boardContainer.parentElement.querySelector('.board-name').textContent;

        const input = boardModal.querySelector(".input-edit-board");
        input.value = boardName;
        showBoardModal();
        input.focus();
    })
});


