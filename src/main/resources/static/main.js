const form = document.querySelector('#media-form');
const player = document.querySelector('#player');

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

if(queryParams.audio){
    const audioPlayer = document.createElement('audio');
    audioPlayer.controls = true;
    audioPlayer.src = `http://localhost:8080/media/${queryParams.audio}`;
    player.appendChild(audioPlayer);

    document.querySelector('#now-playing').innerText = 'Now playing ' + queryParams.audio;
}

form.addEventListener('submit', ev => {
    ev.preventDefault();
    let data = new FormData(form);
    fetch('http://localhost:8080/media', {
        method: 'POST',
        body: data
    }).then(result => result.text()).then(_ => {
        window.location.reload();
    });
});

fetch('http://localhost:8080/media/all')
    .then(result => result.json())
    .then(result => {
        const myMedia = document.querySelector('#your-media');
        if (result['audio/mpeg']) {
            for(let item of result['audio/mpeg']){
                const li = document.createElement('li');
                const link = document.createElement('a');
                link.innerText = item.name;
                link.href = window.location.origin + window.location.pathname + '?audio=' + item.id;
                li.appendChild(link);
                myMedia.appendChild(li);
            }
        }else{
            myMedia.innerHTML = 'No Audio found';
        }

    });
