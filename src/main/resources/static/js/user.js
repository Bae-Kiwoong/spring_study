const userObject = {
	
	init: function() {
		const btnSave = document.getElementById('btn-save');
		const btnModify = document.getElementById('btn-modify');
		const btnDelete = document.getElementById('btn-delete');
				
		if(btnSave) {
			btnSave.addEventListener('click', (e) => {
				e.preventDefault();
				
				this.insertUser();
			})
		}
		
		if(btnModify) {
			btnModify.addEventListener('click', (e) => {
				e.preventDefault();
				this.updateUser();
			})
		}

		if(btnDelete) {
		        btnDelete.addEventListener('click', (e) => {
		            e.preventDefault();
		            this.deleteUser();
             	})        
        }
		
		
		
		},
		
		
		
	
	insertUser: function() {
		const user = {
			username : document.getElementById("username").value,
			password : document.getElementById("password").value,
			email : document.getElementById("email").value						
		}
		
		fetch('/auth/join', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json; charset=UTF-8'
			},
			body: JSON.stringify(user)
		}).then(response => response.json())
		.then(json => {
			alert(json.data);
			
			if(json.status == 400)
				return;
			
			window.location.href = "/";
		}).catch(error => {
			console.error('회원가입 중 오류 발생', error);
		})
		
	},
	
	
	updateUser: function() {
		const user = {
			id: document.getElementById('id').value,
			username: document.getElementById('username').value,
			password: document.getElementById('password').value,
			email: document.getElementById('email').value	
		}
		
		fetch('/auth/info', {
			method: 'POST',
			body: JSON.stringify(user),
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then(response => response.json())
		.then(result => {
			alert(result.data);

			window.location.href="/auth/info";
		}).catch(error => {
			console.error("수정 요청 중 오류발생", error);
		})
    },
	
	deleteUser: function() {
		
		const id = document.getElementById('id').value;
        
        fetch(`/auth/user?id=${id}`, {
            method: 'DELETE',
            
        }).then(response => response.json())
        .then(json => {
            alert(json.data);
            
            window.location.href = "/";
        }).catch(error => {
            console.error('회원탈퇴 중 오류 발생', error);
        })
    }
            
       
	
	
        
        
        
    
            
        
}


userObject.init();