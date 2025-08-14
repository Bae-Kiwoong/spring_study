$(document).ready(function() {
  $('#content').summernote({
	
	height: 400, // 에디터 높이 설정
    lang: 'ko-KR', // 한국어 설정
    placeholder: '내용을 입력하세요...' // 플레이스홀더 설정
	
  });
});

const postObject = {
  init() {
	
    const btnInsert = document.getElementById('btn-insert');
    if (btnInsert) {
      btnInsert.addEventListener('click', (e) => {
        e.preventDefault();
        this.insertPost();
      });
    }
    const btnUpdate = document.getElementById('btn-update');
    if (btnUpdate) {
      btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        this.updatePost();
      });
    }
	const btnDelete = document.getElementById('btn-delete');
	    if (btnDelete) {
      btnDelete.addEventListener('click', (e) => {
        e.preventDefault();
        if (confirm("정말로 삭제하시겠습니까?")) {
          this.deletePost();
        }
      });
    }
  },
  insertPost() {
    const post = {
      title: document.getElementById("title").value,
      content: $('#content').summernote('code'),
    };
    fetch('/post', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=UTF-8'
      },
      body: JSON.stringify(post)
    }).then(response => response.json())
	    .then(json => {
      alert(json.data);
      
      window.location.href = "/";
    }).catch(error => {
      console.error('게시글 등록 중 오류 발생', error);
    });
  },
  updatePost() {
        const idValue = document.getElementById("id").value;
        console.log('수정 요청 id:', idValue); // id 값 확인
        const post = {
            id: idValue,
            title: document.getElementById("title").value,
            content: $('#content').summernote('code'),
        };
        fetch('/post', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify(post)
        }).then(response => response.json())
            .then(json => {
                console.log('서버 응답:', json); // 응답 확인
                alert(json.data);
                window.location.href = "/";
            }).catch(error => {
                console.error('게시글 수정 중 오류 발생', error);
            });
  },
  deletePost() {
    const idValue = document.getElementById("id").value;
    console.log('삭제 요청 id:', idValue); // id 값 확인
    fetch(`/post/${idValue}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json; charset=UTF-8'
      }
    }).then(response => response.json())
      .then(json => {
        console.log('서버 응답:', json); // 응답 확인
        alert(json.data);
        window.location.href = "/";
      }).catch(error => {
        console.error('게시글 삭제 중 오류 발생', error);
      });
  }
}
	

postObject.init();