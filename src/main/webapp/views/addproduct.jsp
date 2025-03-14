<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="./layouts/base.jsp" %>
   <!-- Content -->
    <div class="flex flex-col justify-center items-center">
        <h1 class="text-4xl font-bold">Add a product in stock</h1>
        <p class="text-lg mt-4">Provide all detaill to make system work normally</p>
    </div>

 <form action="${pageContext.request.contextPath}/admin/addproduct" method="post" class="w-[70%] m-auto mt-10">
    <c:forEach var="group" items="${formGroups}">
        <div class="flex justify-between gap-5 mb-2">
            <c:forEach var="field" items="${group}">
                <div class="flex flex-col w-1/2">
                    <label class="text-[14px]">${field.label}</label>
                    <input type="${field.type}" name="${field.name}" placeholder="${field.placeholder}" class='w-full p-3 rounded' required="${field.required}">
                </div>
            </c:forEach>
        </div>
    </c:forEach>

     <p class="text-red-400 text-small">${errorMessage}</p>
     <p class="text-green-400 text-small">${successMessage}</p>

    <div class="mt-4">
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Add Product</button>
    </div>
</form>
  <!-- End Content -->  
<%@ include file="./layouts/footer.jsp" %>