<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/layout/header.jsp" %>


<div class="main_margin_155 containerbox" style="height: 80px;"></div>
<div class="main_margin_85" style="height: 85px;"></div>
<!-- Site Main -->
<main class="containerbox" id="users-mypage">
    <div class="row text-center">
        <h1>마이페이지</h1>
    </div>
    <!-- profile -->
    <div class="row pt-3 justify-content-center" id="user-info">
        <!-- profile img -->
        <div class="col-3 text-center">
            <div class="row justify-content-center">
                <div class="row justify-content-center mb-2">
                    <img class="p-0" id="user_img" src="/resource/img/default_profile.png" alt="">
                </div>
                <div class="row justify-content-center">
                    <label class="" id="profile_upload" for="user_img_in">
									<span><i class="modify_btn fa-sharp fa-solid fa-arrow-up-from-bracket"
                                             style="color: rgb(8,33,71);"></i></span>
                        <span class="text_normal" style="color: rgb(8,33,71);"> 프로필 이미지</span>
                    </label>
                </div>
                <input type="file" name="user_img_in" id="user_img_in" hidden>
            </div>
        </div>

        <!-- input info -->
        <div class="col-5" style="height:318px;">
            <!-- name -->
            <div class="profile_input_group py-2">
                <div class="profile_title">이름</div>
                <div style="display:inline-table;"><input class="form-control modify_input" type="text"
                                                          value="${user.name}" name="user_name" id="user_name"
                                                          maxlength="10">
                </div>
            </div>
            <!-- select sex -->
            <div class="profile_input_group py-2">
                <div class="profile_title">성별</div>
                <button type="button" class="modify_input sex_btn" id="manBtn">남자</button>
                <button type="button" class="modify_input sex_btn" id="womanBtn">여자</button>
                <input type="hidden" name="sex" id="sex" value="${user.sex}">

            </div>
            <!-- birthday -->
            <div class="profile_input_group py-2">
                <div class="profile_title">생년월일</div>
                <div style="display:inline-table;"><input class="form-control modify_input" type="date" min="1920-01-01"
                                                          max="${nowDate}"
                                                          id="user_birthday" name="user_birthday"
                                                          value="${user.birthday}">
                </div>
            </div>
            <!-- phone -->
            <div class="profile_input_group py-2">
                <div class="profile_title">전화번호</div>
                <div style="display:inline-table;"><input class="form-control modify_input" type="text"
                                                          value="${user.phone}" name="user_phone" id="user_phone"
                                                          maxlength="11"
                                                          placeholder='"-"를 제외하고 입력하세요.'>
                </div>
            </div>
            <!-- 전화번호 유효성 검사 및 포맷 -->
            <script>
                $("#user_phone").on("keydown", function () {
                    $(this).val($(this).val().replace(/[^0-9]/ig, ''));
                }).on("keyup", function () {
                    $(this).val($(this).val().replace(/[^0-9]/ig, ''));
                });
            </script>
            <!-- interesting -->
            <div class="profile_input_group pt-2" style="height:50px;">
                <div class="profile_title">관심사</div>
                <div style="display:inline-table; width:300px; padding:0; margin:0;">
                    <input id="interest_input" class="form-control modify_input" type="text" maxlength="10"
                           placeholder="최대4개, 10글자">
                </div>
                <div style="display:inline-table; padding:0; margin:0; transform:translate(0, -3px);">
                    <button class="btn btn-outline-secondary modify_btn" type="button"
                            style="--bs-btn-border-color: rgb(206,212,218);" onclick="addInterest()">등록
                    </button>
                </div>
            </div>
            <!-- interest out -->
            <div id="interest_out"></div>
        </div>
    </div>
    <div class="row justify-content-center mb-5">
        <!-- modify button -->
        <div style="display:inline-table; padding:0; margin-top:0; width:max-content;">
            <button class="btn_base modifyBtn" type="button" id="modifyProfile">내 프로필 수정</button>
            <button class="btn_base modifyBtn" type="button" id="modifyPrivate">개인 정보 수정</button>
        </div>
    </div>

    <hr style="margin: 0; padding: 0; width: 100%">
    <!-- 즐겨찾기 시설 -->
    <div class="row pt-4 pb-3">
        <span class="text_title">내 즐겨찾기</span>
    </div>
    <!-- 시설 카드 -->
    <div class="gym_area mb-5">
            <c:if test="${gyms == [] || gyms == null}">
                <div class="row pt-5 mt-3" style="font-size: large;"><div class="text-center" style="transform: translate(-10px, 0);">즐겨찾기한 시설이 없습니다.</div></div>
            </c:if>
        <div class="gym_cards_box" id="gym_cards_box">
            <c:forEach var="gym" items="${gyms}" varStatus="status">
                <div class="gym_card">
                    <input type="hidden" name="fav_seq" class="fav_seq" value="${favs[status.index]}">
                    <input type="hidden" name="gym_seq" class="gym_seq" value="${gym.gym_seq}">
                    <a href="/detail.gym?gym_seq=${gym.gym_seq}">
                        <c:choose>
                            <c:when test='${gym.gym_main_sysImg != null}'>
                                <img class="gym_img" src="/resource/gym/${gym.gym_main_sysImg}">
                            </c:when>
                            <c:otherwise>
                                <img class="gym_img" src="/resource/img/default02.png">
                            </c:otherwise>
                        </c:choose>
                        <span class="gym_text p-2 ellipsis">
										<span class="pb-2 ellipsis htmlToText">${gym.gym_name}</span><br>
										<span class="ellipsis htmlToText">${gym.gym_phone}</span><br>
										<span class="ellipsis htmlToText">${gym.gym_location}</span><br>
										<span class="ellipsis htmlToText">OPEN:${gym.gym_open} CLOSE:${gym.gym_close}</span>
									</span>
                    </a>
                    <i class="fa-solid fa-heart fa-xl heart" style="color:rgb(207,12,0);"></i>
                </div>
            </c:forEach>
        </div>
    </div>

    <hr style="margin: 0; padding: 0; width: 100%">

    <!-- 내가 쓴 리뷰 -->
    <div class="row pt-4 pb-3">
        <span class="text_title">내가 쓴 리뷰</span>
    </div>

    <!-- 리뷰 카드 영역 -->
    <div class="row review_cards_area" id="review_cards_area">
        <!-- review card -->


        <c:if test="${reviews == [] || reviews == null}">
            <div class="row pt-5 mt-3" style="font-size: large;"><div class="col text-center"  style="transform: translate(7px, 0);" >작성된 리뷰가 없습니다.</div></div>
        </c:if>
        <c:forEach var="review" items="${reviews}">
            <div class="col-6 review_card p-1">
                <form action="/detail.gym" class="review_detail">
                    <input type="hidden" name="gym_seq" class="gym_seq" value="${review.gym_seq}">
                    <input type="hidden" name="review_seq" class="review_seq" value="${review.review_seq}">
                    <!-- review_seq 저장 -->
                </form>

                <div class="border p-1  my-3">
                    <div class="row py-1">
                        <div class="col-10 review_gymName review_detail_starter">${review.gym_name}</div>
                        <div class="col-1">
                            <a href="/reviewModify.gym?review_seq=${review.review_seq}" class="modify_review_btn">
                                <i class="fa-regular fa-pen-to-square"></i>
                            </a>
                        </div>
                        <div class="col-1 text-start">
                            <i class="fa-solid fa-x del_review_btn"></i>
                        </div>
                    </div>

                    <hr>

                    <div class="review_text review_detail_starter">${review.review_contents}</div>
                </div>
            </div>
        </c:forEach>


        <script>
            var reviewText = document.getElementsByClassName("review_text");
            for (var i = 0; i < reviewText.length; i++) {
                reviewText[i].innerText = reviewText[i].innerHTML;
            }
        </script>
    </div>


    <!-- 개인정보 수정 테이블 -->

    <form id="form_pw" class="modal-overlay" action="/pw.userMyPage" method="post">
        <input type="hidden" name="userSeq" value="${userSeq}">
        <div id="private_table">
            <!-- X icon -->
            <i class="fa-solid fa-x" id="close_private"></i>


            <!-- title -->
            <span id="private_title">
							개인정보 수정
						</span>
        </div>
        <div id="pw" class="text-center">
            <!-- 1차 비밀번호 입력 -->
            <div class="input_pw">
                <span>새 비밀번호</span>
                <input id="pw1" name="pw" type="password" placeholder="8~16자, 영어,숫자,특수문자" maxlength="16">
            </div>
            <!-- 2차 비밀번호 입력 -->
            <div class="input_pw">
                <span>새 비밀번호 확인</span>
                <input id="pw2" type="password" placeholder="비밀번호 확인" maxlength="16">
            </div>

            <div id="pw_confirm" style="height: 30px;color:red;"></div>

            <button type="button" id="pw_save_btn" class="btn_base"
                    style="margin: auto; margin-top:40px;">저장하기
            </button>
        </div>
        <!-- 회원탈퇴 -->
        <div id="sign_down">
            <div class="sign_down_area" id="sign_down_btn_area">
                <a onclick="signDown()" id="sign_down_btn">회원탈퇴</a>
            </div>
            <div class="sign_down_area" id="sign_down_confirm">
                <div>정말 탈퇴하시겠습니까?</div>
                <button type="button" class="sign_down_confirm_btn" id="confirm_y">예</button>
                <button type="button" class="sign_down_confirm_btn" id="confirm_n">아니오</button>
            </div>
        </div>
    </form>
    <form action="/signDown.userMyPage" id="sign_down_form" method="post">
        <input type="hidden" name="userSeq" value="${userSeq}">
    </form>


    <script>


        var htmlToText = document.getElementsByClassName("htmlToText");
        for (var i = 0; i < htmlToText.length; i++) {
            htmlToText[i].innerText = htmlToText[i].innerHTML;
        }

        ///////////////////////////////////////////// 프로필 //////////////////////////////////////////////////////////////////

        var afterPi = ""; // 미리보기 전에 미리 선언 됨
        var pi_check = false;
        var sel_file;

        $("#user_img_in").on("change", handleImgFileSelect);

        //이미지 미리보기
        function handleImgFileSelect(e) {
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);

            if (files.length > 0) { // 취소 누르면 files 길이가 0이 됨. 파일이 있으면 원래대로 처리

                var reg = /(.*?)\/(jpg|jpeg|png|bmp|pdf|gif)$/;

                filesArr.forEach(function (f) {
                    if (!f.type.match(reg)) {
                        Swal.fire({
                            icon: 'error',
                            title: '이미지 업로드 불가',
                            text: '이미지 파일만 업로드 가능합니다.',
                            confirmButtonText: '확인'
                        })
                        return;
                    }

                    sel_file = f;

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("#user_img").attr("src", e.target.result);
                    }
                    reader.readAsDataURL(f);
                });

                pi_check = true;
            } else { // 취소를 눌렀을 때 행동
                let basPi = "${user.pi}"; // 페이지를 불러왔을 때의 사진 경로
                if (afterPi != null && afterPi != "") { // 페이지에서 프사를 한번 바꿨을 때(저장을 한번 된 상태-데이터테이블+이미지저장)
                    $("#user_img").attr("src", "/resource/profileImg/" + afterPi); // 취소를 누르면 원래 값으로 돌아가기
                } else if (basPi != "/resource/profileImg/") { // 파일 비어 있으면 이렇게 옴
                    $("#user_img").attr("src", basPi); // 취소를 눌러서 파일이 없으면 처음 저장된 아이 가져옴.
                } else { // 둘다 아니라면 기본 이미지 보내기 - 원래 프사가 없던 애
                    $("#user_img").attr("src", "/resource/img/default_profile.png");
                }
                pi_check = false; // 취소하기 누르면 null 들어가고 기본 이미
            }
        }

        //이미지 저장
        function fn_submit() {

            var form = new FormData();
            form.append("user_img_in", $("#user_img_in")[0].files[0]);

            $.ajax({
                url: "/modifyPI.userMyPage"
                , type: "POST"
                , processData: false
                , contentType: false
                , data: form
                , success: function (response) {
                    afterPi = response; // 성공하면 afterPi(이미지이름)를 보내줌
                }
                , error: function (jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        }

        ////////////////////////


        let profile_upload = $("#profile_upload");
        profile_upload.on("click", function () {

        });

        //성별 기존 설정
        if ($("#sex").val() == "M") {
            $("#manBtn").css("border", "1px solid #001A41").css("color", "#001A41");
            $("#womanBtn").css("border", "1px solid #cbcbcb").css("color", "#cbcbcb");
        } else if ($("#sex").val() == "W") {
            $("#womanBtn").css("border", "1px solid #001A41").css("color", "#001A41");
            $("#manBtn").css("border", "1px solid #cbcbcb").css("color", "#cbcbcb");
        }

        // 성별 고르는 이벤트
        $("#manBtn").on("click", function () {
            $("#sex").val("M");
            $("#manBtn").css("border", "1px solid #001A41").css("color", "#001A41");
            $("#womanBtn").css("border", "1px solid #cbcbcb").css("color", "#cbcbcb");
        })
        $("#womanBtn").on("click", function () {
            $("#sex").val("W");
            $("#womanBtn").css("border", "1px solid #001A41").css("color", "#001A41");
            $("#manBtn").css("border", "1px solid #cbcbcb").css("color", "#cbcbcb");
        })

        // 떨림 애니메이션
        function wobble(element) {
            let order = [0, -8, 8, -4, 4, -2, 2, -1, 1, 0];
            let seq = [];
            order.forEach(function (y) {
                seq.push({transform: "translate(0," + y + "px)"});
            });
            element.animate(seq, {duration: 300});
        }

        //관심사 추가하기
        function addInterest() {
            let interest_input = $("#interest_input").val();
            if ($(".interesting").length >= 4) {
                wobble(document.getElementById("interest_input"));
                return false;
            }
            if (interest_input != "") {
                let interest_out = $("#interest_out");

                let interesting = $("<div>");
                interesting.addClass("interesting");
                interesting.html($("<span>").addClass("user_interest").text(interest_input));

                let del_interest = $("<a>").on("click", function () {
                    $(this).parent().remove();
                });
                del_interest.html(" X");
                del_interest.addClass("modify_btn");
                del_interest.attr("style", "cursor:pointer; color:rgb(104, 104, 204); text-decoration: none;");

                interesting.append(del_interest);

                interest_out.append(interesting);
                $("#interest_input").val("");
            }
        }

        // 관심사 입력에 엔터로 이벤트 발생
        $("#interest_input").on("keyup", function (e) {
            if (e.keyCode == 13) {
                addInterest();
            }
            ;
        });

        // 관심사 초기 생성을 위한 함수.
        function interestBase(interest_input) {
            if ($(".interesting").length >= 4) {
                wobble(document.getElementById("interest_input"));
                return false;
            }
            if (interest_input != "") {
                let interest_out = $("#interest_out");

                let interesting = $("<div>");
                interesting.addClass("interesting");
                interesting.html($("<span>").addClass("user_interest").text(interest_input));

                let del_interest = $("<a>").on("click", function () {
                    $(this).parent().remove();
                });
                del_interest.html(" X");
                del_interest.addClass("modify_btn");
                del_interest.attr("style", "cursor:pointer; color:rgb(104, 104, 204); text-decoration: none;");

                interesting.append(del_interest);

                interest_out.append(interesting);
                $("#interest_input").val("");
            }
        }

        //초기 관심사 생성
        //let interests = JSON.parse("${user.interest}");


        <c:if test="${user.interest != null}">
        let interests = JSON.parse(${user.interest});
        interests.forEach(inter => interestBase(inter));
        </c:if>


        // 프로필 영역 기본값
        $(".modify_input").attr("disabled", "true");
        $(".modify_btn").css("display", "none");
        $("#profile_upload").css("cursor", "default");
        $("#user_img_in").attr("disabled", "true");
        $("sex_btn").css("cursor", "default");
        // 프로필 이미지 초기 세팅
        let root = "${user.pi}";
        if (root != "/resource/profileImg/null" && root != "/resource/profileImg/") {
            $("#user_img").attr("src", root);
        }
        // 프로필 수정하기 버튼 클릭 이벤트
        $("#modifyProfile").on("click", modifyProfile);
        let modifyBtn = $("#modifyProfile");

        // 프로필 수정버튼 누를 때
        function modifyProfile() {
            modifyBtn.html("변경 저장");
            $(".modify_input").removeAttr("disabled");
            $("#user_img_in").removeAttr("disabled");
            $(".modify_btn").css("display", "inline-table");
            $("#profile_upload").css("cursor", "pointer");
            $("#manBtn").addClass("sex_btn_hover");
            $("#womanBtn").addClass("sex_btn_hover");
            $("sex_btn").css("cursor", "pointer");
            modifyBtn.off("click");
            modifyBtn.on("click", submitProfile);
        };

        // 저장버튼 누를 때
        function submitProfile() {
            let fix_name = $("#user_name").val();
            let fix_sex = $("#sex").val();
            let fix_birthday = $("#user_birthday").val();
            let fix_phone = $("#user_phone").val();
            let fix_interests = document.querySelectorAll(".user_interest");
            //let fix_interests = $(".user_interest");


            let interest_list = [];
            fix_interests.forEach(item => {
                interest_list.push(item.innerHTML);
            });

            $.ajax({
                url: "/fixProfile.userMyPage",
                data: {
                    name: fix_name,
                    sex: fix_sex,
                    birthday: fix_birthday,
                    phone: fix_phone,
                    interest: JSON.stringify(interest_list),
                    userSeq: "${userSeq}"
                },
                type: "post",
                success: function () {
                }
            });

            modifyBtn.html("프로필 수정");
            $(".modify_input").attr("disabled", "true");
            $(".modify_btn").css("display", "none");
            $("#profile_upload").css("cursor", "default");
            $("#user_img_in").attr("disabled", "true");
            $("#interest_input").val("");
            $("#manBtn").removeClass("sex_btn_hover");
            $("#womanBtn").removeClass("sex_btn_hover");
            $("sex_btn").css("cursor", "default");
            modifyBtn.off("click");
            modifyBtn.on("click", modifyProfile);
            if (pi_check) {
                fn_submit();
            }
        };

        ///////////////////////////////////// My 시설 ////////////////////////////////////////////////////

        // 시설카드
        //createGymCard();


        $(".heart").on("click", function () {
            var heart = $(this);
            if ($(this).css("color") == "rgb(143, 149, 154)") {
                $.ajax({
                    url: "/addHeart.userMyPage",
                    data: {
                        fav_seq: $(heart).closest(".gym_card").find(".fav_seq").val(),
                        gym_seq: $(heart).closest(".gym_card").find(".gym_seq").val()
                    },
                    type: "POST",
                    success: () => {
                        $(heart).css("color", "#CF0C00");
                    }
                });
            } else {
                //즐겨찾기 취소
                $.ajax({
                    url: "/delHeart.userMyPage",
                    data: {
                        fav_seq: $(heart).closest(".gym_card").find(".fav_seq").val()
                    },
                    type: "POST",
                    success: () => {
                        $(heart).css("color", "#8f959a")
                    }
                });
            }
        });

        /////////////////////////////////////// My 리뷰 ///////////////////////////////////////////////////////////////////


        // 리뷰 X 버튼 이벤트
        $(".del_review_btn").on("click", function () {
            var del = $(this);
            $.ajax({
                url: "/delReview.userMyPage",
                data: {review_seq: $(this).closest(".review_card").find(".review_seq").val()},
                type: "POST",
                success: function () {
                    $(del).closest(".review_card").remove();
                }
            });
        });

        //리뷰 상세페이지로 이동
        $(".review_detail_starter").on("click", function () {
            $(this).closest(".review_card").find("form").submit();
        });

        // 개인정보 수정 폼 닫기
        $("#close_private").click(() => {
            $("#form_pw").css("display", "none");
            $("#pw1").val("");
            $("#pw2").val("");
            $("#sign_down_btn_area").css("display", "block");
            $("#sign_down_confirm").css("display", "none");
        });

        //회원 탈퇴
        function signDown() {
            $("#sign_down_btn_area").css("display", "none");
            $("#sign_down_confirm").css("display", "block");
        };

        $("#confirm_n").click(function () {
            $("#sign_down_btn_area").css("display", "block");
            $("#sign_down_confirm").css("display", "none");
        });
        $("#confirm_y").click(function () {
            $("#sign_down_form").submit();
        });

        // 개인 정보 수정 버튼 동작
        $("#modifyPrivate").click(function () {
            $("#form_pw").css("display", "");
        });

        // password confirm
        $("#pw1").on("keydown", () => {
            $("#pw_confirm").html("");
        });
        $("#pw2").on("keydown", () => {
            $("#pw_confirm").html("");
        });

        let pwRegex_mypage = /^(?=.*[A-Za-z\d])(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
        // 비밀번호 저장 버튼 이벤트
        $("#pw_save_btn").on("click", () => {
            // regex Exception
            if ($("#pw1").val() == "") {
                wobble(document.getElementById("pw1"));
                $("#pw_confirm").html("비밀번호를 입력하세요.");
                $("#pw1").val("").focus();
                return false;
            }
            if (!pwRegex_mypage.test($("#pw1").val())) {
                wobble(document.getElementById("pw1"));
                $("#pw_confirm").html("입력이 옳바르지 않습니다.");
                $("#pw1").val("").focus();
                $("#pw2").val("");
                return false;
            }
            // same pw Exception
            if ($("#pw1").val() != $("#pw2").val()) {
                wobble(document.getElementById("pw2"));
                $("#pw_confirm").html("비밀번호가 일치하지 않습니다.");
                $("#pw2").focus();
                return false;
            }
            //submit
            $("#form_pw").submit();
        });

        $("#sign_down_confirm").css("display", "none");
        $("#form_pw").css("display", "none");


    </script>
</main>

<%@ include file="/layout/footer.jsp" %>