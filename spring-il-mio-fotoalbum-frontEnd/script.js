const PHOTOS_URL = 'http://localhost:8080/api/photos';
const MESSAGE_URL = 'http://localhost:8080/api/mailbox';

const contentElement = document.getElementById('photos-gallery');
const toggleForm = document.getElementById('toggle-form');
const messageForm = document.getElementById('message-form');
const searchForm = document.getElementById('search-form');
const keyword = document.getElementById('keyword');

// API REQUESTS
const getAllPhotos = async () => {
    const param = keyword.value;
    console.log(keyword);
    const response = await fetch(PHOTOS_URL + '?keyword=' + param);
    return response;
};

//post message 
const postMessage = async (jsonData) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData,
    };
    const response = await fetch(MESSAGE_URL, fetchOptions);
    return response;
};


//DOM MANIPULATION

const toggleFormVisibility = () => {
    document.getElementById('form').classList.toggle('d-none');
};

const createPhotoItem = (item) => {

    let category = "";
    item.categories.forEach(function (categ) {
        category += " #" + categ.name;
    });

    return `<div class="col-4" id="photo-card">
                <div class="card h-100">
                    <div class="card-body p-0">
                        <img style="max-width: 100%;" src="${item.url}" alt="${item.description}">
                    </div>
                    <div class="card-footer">
                    <h5>${item.title}</h5>
                    <h6>${item.description}</h6>
                    <h6 class="text-info"> ${category} </h6>
                    </div>
                </div>
        </div>`;

};

// print element in page 
const createPhotoList = (data) => {
    console.log(data);
    let list = `<div class="row gy-3">`;
    data.forEach((element) => {
        list += createPhotoItem(element);
    });
    list += '</div>';
    return list;
};



const loadData = async () => {
    const response = await getAllPhotos();
    console.log(response);
    if (response.ok) {
        const data = await response.json();
        console.log(data);
        contentElement.innerHTML = createPhotoList(data);
    };
}

//save new Message
const saveMessage = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const formDataObj = Object.fromEntries(formData.entries());
    const formDataJson = JSON.stringify(formDataObj);
    const response = await postMessage(formDataJson);


    if (response.ok) {
        loadData();
        event.target.reset();
    } else {
        console.log('ERROR', response.status, responseBody);
    }
};


toggleForm.addEventListener('click', toggleFormVisibility);
messageForm.addEventListener('submit', saveMessage);
searchForm.addEventListener('submit', loadData);

loadData();

const menuBtn = document.querySelector('.menu-btn');
let menuOpen = false;
menuBtn.addEventListener('click', () => {
    if (!menuOpen) {
        menuBtn.classList.add('open');
        menuOpen = true;
    } else {
        menuBtn.classList.remove('open');
        menuOpen = false;
    }
});