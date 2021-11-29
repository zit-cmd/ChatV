<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<script type="text/javascript">
	$.ajax({
	    url: "http://localhost:8080/chat_servlet/load?sender="+${USER.id},
	    type: 'GET',
	    contentType: 'application/json',
	    dataType: 'json',
	    success: function (result) {
	    	showList(result);
	    },
	    error: function (error) {
	    }

	});
	
	function showList(result) {
		let html = ""
		const listElement = document.querySelector('.chats__list')
		result.map( item => {
			return html += `
				<li class="chats__chat chat">
					<a href="#">
						<div class="chats__chat friend friend--lg">
								<div class="friend__column">
									<img src="images/2.jpg" class="m-avatar friend__avatar" />
									<div class="friend__content">
										<span class="friend__name">${item.friendName}</span>
										<span class="friend__bottom-text">${item.massage.content}</span>
									</div>
								</div>
								<div class="friend__column">
									<div class="friend__now-listening">
										<span class="chat__timestamp"> August 14 </span>
									</div>
								</div>
						</div>
					</a>
				</li>
			`
		})

		listElement.innerHTML = html;
	}
	
</script>
<header class="header">
	<div class="header__header-column">
		<h1 class="header__title">Chats</h1>
	</div>
	<div class="header__header-column">
		<span class="header__icon"> <i class="fas fa-search"></i>
		</span> <span class="header__icon"> <i class="fas fa-comment"></i>
		</span> <span class="header__icon"> <i class="fas fa-cog"></i>
		</span>
	</div>
</header>
<main class="Chats">
	<ul class="chats__list">
<%--		<li class="chats__chat chat">--%>
<%--			<a href="chat.html">--%>
<%--				<div class="chats__chat friend friend--lg">--%>
<%--					&lt;%&ndash; <c:forEach var = "friend" items="${USER.listFriends}"> &ndash;%&gt;--%>
<%--						<div class="friend__column">--%>
<%--							<img src="images/2.jpg" class="m-avatar friend__avatar" />--%>
<%--							<div class="friend__content">--%>
<%--								<span class="friend__name"> Hermione </span> --%>
<%--								<span class="friend__bottom-text"> Me? Books and cleverness. </span>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--						<div class="friend__column">--%>
<%--							<div class="friend__now-listening">--%>
<%--								<span class="chat__timestamp"> August 14 </span>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--				&lt;%&ndash; 	</c:forEach> &ndash;%&gt;--%>
<%--				</div>--%>
<%--			</a>--%>
<%--		</li>--%>
	</ul>
</main>