// 댓글 등록 정보 서버로 전달
async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method:'post',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
// 댓글 등록 이벤트
// document.getElementById('postBtn').addEventListener('click', ()=>{
//     let contentVal = document.getElementById('cmtContent');
//     if(contentVal.value == '' || contentVal.value == null){
//         alert("댓글을 입력해 주세요.");
//         contentVal.focus();
//         return false;
//     }else{
//         let cmtData = {
//             bno: bnoVal,
//             writer: cmtWriter,
//             content: contentVal.value
//         }
//         postCommentToServer(cmtData).then(result =>{
//             if(result == 1){
//                 alert("댓글 등록 성공");
//             }
//             getCommentList(bnoVal);
//         })
//     }
// })

async function getCommentListFromServer(bno){
    try {
        let resp = await fetch("/comment/list/"+bno);
        let result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}
// 댓글 화면에 뿌리기
function getCommentList(bno){
    getCommentListFromServer(bno).then(result =>{
        let div = document.getElementById('commentArea');
        div.innerHTML = "";
        if(result.length > 0){
            let table = `<table><tr><th>작성자</th><th>댓글내용</th><th>작성일</th><th></th></tr>`;
            for (const cvo of result) {
                table += `<tr data-cno="${cvo.cno}" data-writer="${cvo.writer}" data-content="${cvo.content}">`;
                table += `<td>${cvo.writer}</td><td>${cvo.content}</td><td>${cvo.regDate}</td>`;
                // 접속자와 작성자가 같아야 수정/삭제
                if(cmtWriter == cvo.writer){
                    table += `<td><div class="button-box"><button class="button" type="button" id="cmtModBtn">수정</button>`;
                    table += `<button class="button" type="button" id="cmtDelBtn">삭제</button></div></td></tr>`;
                }else{
                    table += `<td><div class="button-box"><button id="likeBtn" class="like-btn button position-relative" type="button">좋아요`;
                    table += `<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">`;
                    table += `${cvo.likeCount}<span class="visually-hidden">unread messages</span></span></button>`;
                    table += `<button class="report-btn button" type="button">신고</button></div></td></tr>`;
                }
            }
            table += `</table>`;
            div.innerHTML = table;
        }
    })
}
// 댓글 수정 정보 서버로 전달
async function commentPutToServer(cmtData){
    try {
        let url = "/comment/put";
        let config = {
            method:"put",
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        let resp = await fetch(url, config);
        let result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
// 댓글 삭제 정보 서버로 전달
async function commentDeleteToServer(cno){
    try {
        let url = "/comment/delete/" + cno;
        let config = {method : 'delete'};
        let resp = await fetch(url, config);
        let result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 댓글 수정/삭제 이벤트
document.addEventListener('click', e => {
    let div = document.getElementById('commentModArea');
    console.log(div);
    console.log(e.target.classList);
    console.log(e.target.id);
    if(e.target.classList.contains('fa-xmark')) div.style.display = 'none';
    
    if(e.target.id == 'cmtModBtn'){
        let tr = e.target.closest('tr');
        let cnoVal = tr.dataset.cno;
        let writerVal = tr.dataset.writer;
        let contentVal = tr.dataset.content;
        div.style.display = 'block';

        div.innerHTML = `<div data-cno="${cnoVal}" data-writer="${writerVal}" class="mod-box">
        <input type="hidden" class="" value="${writerVal}">
        <span class="">작성자(${cmtNick})</span>
        <span>댓글입력 <input type="text" id="cmtModContent" value="${contentVal}"}></span>
        <button type="button" id="putBtn" class="button comment-btn">수정</button>
        <span class="modal-x" id="exitModal"><i class="fa-solid fa-xmark"></i></span>
        </div>`;

    } else if(e.target.id == 'postBtn'){
        let contentVal = document.getElementById('cmtContent');
        if(contentVal.value == '' || contentVal.value == null){
            alert("댓글을 입력해 주세요.");
            contentVal.focus();
            return false;
        }else{
            let cmtData = {
                bno: bnoVal,
                writer: cmtWriter,
                content: contentVal.value
            }
            postCommentToServer(cmtData).then(result =>{
                if(result == 1){
                    alert("댓글 등록 성공");
                }
                getCommentList(bnoVal);
            })
        }
    } else if(e.target.id == 'putBtn'){
        let div = e.target.closest('div');
        console.log(div.dataset.cno);
        let cnoVal = div.dataset.cno;
        let writerVal = div.dataset.writer;
        let cmtContent = document.getElementById('cmtModContent');

        if(cmtContent.value == null || cmtContent.value == ''){
            alert("댓글을 입력해 주세요.");
            cmtContent.focus();
            return false;
        } else {
            let cmtData = {
                cno: cnoVal,
                writer: writerVal,
                content: cmtContent.value
            }
            commentPutToServer(cmtData).then(result =>{
                if(result == 1){
                    alert('댓글 수정 성공');
                    div.style.display = 'none';
                    getCommentList(bnoVal);
                }
            })
        }
    } else if(e.target.id == 'cmtDelBtn'){
        let tr = e.target.closest('tr');
        let cnoVal = tr.dataset.cno;
        commentDeleteToServer(cnoVal).then(result =>{
            if(result == 1){
                alert("댓글 삭제 성공");
                getCommentList(bnoVal);
            }
        })
    } else if(e.target.id == 'likeBtn'){
        let tr = e.target.closest('tr');
        let cnoVal = tr.dataset.cno;
        console.log("cno : " + cnoVal);
        commentLikeToServer(cnoVal).then(result =>{
            if(result == 1){
                alert("좋아용!!");
                getCommentList(bnoVal);
            }
        })
    }
})


async function commentLikeToServer(cno){
    try{
        let resp = await fetch("/comment/like/" + cno);
        let result = await resp.text();
        return result;
    } catch(error){
        console.log(error);
    }
}