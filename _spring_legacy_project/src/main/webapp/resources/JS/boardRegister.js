document.getElementById('file-trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
})

// 실행파일, 이미지파일에 대한 정규 표현식 작성
const regExp = new RegExp("\.(exe|sh|js|bat|msi|dll)$"); // 실행파일 체크
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$"); // 허용할 이미지 파일 체크
const maxSize = 1024*1024*20; // 20MB 파일 최대 사이즈

// 파일 타입, 사이즈 체크
function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){ // 이미지파일이 아니면 0
        return 0;
    }else if(regExp.test(fileName)){ // 실행파일이면 0
        return 0;
    }else if(fileSize > maxSize){ // max사이즈보다 크면 0
        return 0;
    }else{ // 모두 해당 안되면 1
        return 1;
    }
}

document.addEventListener('change', (e) =>{
    if(e.target.id == 'files'){
        // 파일을 다시 추가할 때는 버튼 상태를 원래대로 변경
        document.querySelector('.post-btn').disabled = false;

        // input file element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);
        // 첨부파일 정보를 file-zone에 기록
        let fileZone = document.getElementById('files-zone');
        // 기존 값이 있다면 삭제
        fileZone.innerHTML = "";
        let isOk = 1;
        let div = `<div class="file-list">`;
        for (const file of fileObj) {
            let validResult = fileValidation(file.name, file.size); // 0 또는 1로 리턴
            isOk *= validResult;
            div += `<div>${file.name}<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${validResult ? '가능' : '불가능'}</span></div>`;
        }
        div += `</div>`;
        fileZone.innerHTML = div;

        if(isOk == 0){
            document.querySelector('.post-btn').disabled = true;
        }
    }
})
