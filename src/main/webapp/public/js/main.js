// Ẩn hiện modal
axios.withCredentials = true;
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
    card.addEventListener("submit",async (e) => {
        e.preventDefault();
        console.log(e)
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
    });

});

// Submit card
function submitCard(type) {
    const formEditCard = document.getElementById("form-edit-card");
    formEditCard.addEventListener("submit", async (event) => {
        try {
            event.preventDefault();
            console.log(event);
            //const type = "update" or "delete";
            const boardId = formEditCard["2"].value;
            const listId = formEditCard["1"].value
            const cardId = formEditCard["0"].value
            const description = formEditCard["3"].value
            console.log({
                cardId,
                description,
                boardId,
                listId
            })
            if(type == "update"){
                 await axios.put(`/boards/${boardId}/lists/${listId}/cards/${cardId}`, {
                    boardId,
                    listId,
                    cardId,
                    description
                 })
             }else{
                 await axios.delete(`/boards/${boardId}/lists/${listId}/cards/${cardId}`)
             }

            location.reload();
        }catch (e) {
            console.log(e)
            location.reload();
        }
    })
}
function submitList(type){
    const formEditList = document.getElementById("form-edit-list");
    formEditList.addEventListener("submit", async (event) => {
        try {
            event.preventDefault();
            console.log(event);
            //const type = "update" or "delete";
            const boardId = formEditList["1"].value;
            const listId = formEditList["0"].value
            const name = formEditList["2"].value
            console.log({
                name,
                boardId,
                listId
            })
            if(type == "update"){
                await axios.put(`/boards/${boardId}/lists/${listId}`, {
                    boardId,
                    listId,
                    name
                })
            }else{
                await axios.delete(`/boards/${boardId}/lists/${listId}`, { boardId, listId})
            }

            location.reload();
        }catch (e) {
            console.log(e)
            location.reload();
        }
    })
}
function handlePagePaginate(event, index){
    event.preventDefault();
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    const search = params.search;
    if(search){
        window.location.href = `/boards?page=${index}&search=${search}`
    }else{
        window.location.href = `/boards?page=${index}`
    }
}
document.getElementById("form-update-profile")?.addEventListener("submit", async (event) => {
    try {
        event.preventDefault();
        const name = document.getElementById("fullname").value;
        console.log(name)
        await axios.put("/settings/profile", { name })
        location.reload();
    }catch (e) {
        location.reload();
        console.log(e)
    }
})
document.getElementById("form-reset-password")?.addEventListener("submit", async (event) => {
    try {
        event.preventDefault();
        const oldPassword = document.getElementById("oldPassword").value
        const newPassword = document.getElementById("newPassword").value
        await axios.put("/settings/reset-password", { oldPassword, newPassword })
        location.reload();
    }catch (e) {
        console.log(e)
        if(e.response.status === 409) {
            document.getElementById("passwordError").style.display = "block"
            document.getElementById("passwordError").innerHTML= "invalid password";
        }
    }
})
function increasePage(event){
    event.preventDefault();
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    const search = params.search;
    let page = params.page;
    page = page? Number(page)+1: 1
    if(search){
        window.location.href = `/boards?page=${page}&search=${search}`
    }else{
        window.location.href = `/boards?page=${page}`
    }
}
function decreasePage(event){
    event.preventDefault();
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    const search = params.search;
    let page = params.page;
    page = page? Number(page) - 1: 1
    if(search){
        window.location.href = `/boards?page=${page}&search=${search}`
    }else{
        window.location.href = `/boards?page=${page}`
    }
}
async function handleEditBoard(event, type){
    event.preventDefault();
    const formEditBoard = document.getElementById("form-edit-board")
    try {
        const id = formEditBoard["0"].value
        const name = formEditBoard["1"].value;
        if(type === "update") await axios.put(`/boards/${id}`, { id, name})
        else await axios.delete(`/boards/${id}`);
        location.reload();
    }   catch (e) {
        location.reload();
        console.log(e)
    }
}