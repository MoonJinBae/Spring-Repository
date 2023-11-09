
document.addEventListener('click', (e) =>{
    let tr = e.target.closest('tr');
    if(tr){
        let bno = tr.dataset.bno;
        document.getElementById(`bno`+bno).click();
    }
})