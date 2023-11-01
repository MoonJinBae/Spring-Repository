
// 서버에서 리스트 받아오기
async function spreaFileFromServer(bno){
    try {
        const resp = await fetch('/board/spread/'+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 파일목록 화면에 뿌리기
function getFileList(bno){
    let dl = document.getElementById('files-ul');
    dl.innerHTML = "";
    spreaFileFromServer(bno).then(result =>{
        for(let fvo of result){
            console.log(fvo.saveDir.replace(/[\\]/g, '/')+'/'+fvo.uuid+'_th_'+fvo.fileName);
            let li = `<dd data-uuid="${fvo.uuid}"><div class="file-div"><div><span class="tumbnail"><img src="/upload/${fvo.saveDir.replace(/[\\]/g, '/')+'/'+fvo.uuid+'_th_'+fvo.fileName}" /></span>`;
            li += `<span>파일명▶${fvo.fileName}</span>`;
            li += `<span>파일크기▶${fvo.fileSize} BYTE</span>`;
            li += `<span>등록일▶${fvo.regDate}</span></div>`;
            if(email == writer){
                li += `<div><button style="color:#fff" class="btn btn-outline-secondary" type="button" id="file-del-btn">삭제</button></div></div></dd>`;
            }else{
                li += `</div></dd>`;
            }
            dl.innerHTML += li;
        }
    })
}

async function deleteFileToServer(uuid){
    try {
        const url = '/board/'+uuid;	
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
// 파일 삭제
document.addEventListener('click',(e) =>{
    // 파일 삭제 버튼이 맞으면...
    if(e.target.id == 'file-del-btn'){
        let dd = e.target.closest('dd');
        let uuid = dd.dataset.uuid;
        deleteFileToServer(uuid).then(result =>{
            if(result == 1){
                alert("파일 삭제 완료!!");
            }else{
                alert("파일 삭제 실패");
            }
            getFileList(bnoVal);
        })
    }
})
