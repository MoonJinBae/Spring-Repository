// 파일 리스트 받아오기
async function serverFromFileList(bno){
    try {
        const resp = await fetch("/board/spreadFile/"+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 파일 리스트 화면에 출력
function getFileList(bno){
    serverFromFileList(bno).then(bdto => {
        let writer = bdto.bvo.writer;
        const div = document.querySelector('.detail-files');
        div.innerHTML = "";
        if(bdto.fvo.length > 0){
            let table = `<table><tr><th>파일명</th><th>파일크기</th><th>업로드일자</th><th>파일삭제</th></tr>`;
            for (const fvo of bdto.fvo) {
                table += `<tr data-uuid="${fvo.uuid}"><td><span>${fvo.fileName}</span></td>
                <td><span>${fvo.fileSize}(BYTE)</span></td>
                <td><span>${fvo.regDate}</span></td>
                <td><div class="button-box">`;
                if(email == writer){
                    table += `<button type="button" id="removeFileBtn" class="button fileDel-btn">삭제</button></div></td></tr>`;
                }else{
                    table += `</div></td></tr>`;
                }
            }
            table += `</table>`;
            div.innerHTML = table;
        }
    })
}

// 파일삭제 정보 서버로 전달
async function removeFileToServer(uuid){
    try {
        let url = "/board/removeFile/"+uuid;
        let config = {method:'delete'};
        let resp = await fetch(url, config);
        let result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }

}

// 파일 삭제 이벤트
document.addEventListener('click', e =>{
    let tr = e.target.closest('tr');
    console.log(e.target.id);
    if(e.target.id == 'removeFileBtn'){
        let uuid = tr.dataset.uuid;
        removeFileToServer(uuid).then(result =>{
            if(result == 1){
                alert("파일 삭제 성공");
            }
            getFileList(bnoVal);
        })
    }
})

