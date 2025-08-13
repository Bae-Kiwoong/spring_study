//로그인 페이지에서 버튼을 누르면 입력한 아이디,비번이 서버로 전송
//요청 메서드:post , 요청주소:/auth/login

const btnLogin = document.querySelector('#btn-login');

btnLogin.addEventListener('click', (e) => {

		    e.preventDefault();
    
    const user = {
        username: document.querySelector('#username').value,
        password: document.querySelector('#password').value
    };

	
	
	 fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(user)
    })
    .then(response => response.json())
    .then(json => {
       	alert(json.data);
		
		if(json.status == 200) 
			
			window.location.href = '/';
		
        
    })
    .catch(error => {
        console.error('로그인 요청 실패', error);
    });
	
    
});
	
