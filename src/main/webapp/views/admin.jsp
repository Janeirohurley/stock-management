  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ include file="./layouts/base.jsp" %>
      <!-- Content -->
        
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
                <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
                    <div class="flex justify-between mb-6">
                        <div>
                            <div class="flex items-center mb-1">
                                <div class="text-2xl font-semibold">${totalProducts}</div>

                                <div class="p-1 rounded bg-emerald-500/10 text-emerald-500 text-[12px] font-semibold leading-none ml-2">${quantiteProduct}</div>
                            </div>
                            <div class="text-sm font-medium text-gray-400">Total products</div>
                        </div>
                    </div>

                    <a href="/gebruikers" class="text-[#f84525] font-medium text-sm hover:text-red-800">View</a>
                </div>
                <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
                    <div class="flex justify-between mb-4">
                        <div>
                            <div class="flex items-center mb-1">
                                <div class="text-2xl font-semibold">${totalMontant}</div>
                                <div class="p-1 rounded bg-emerald-500/10 text-emerald-500 text-[12px] font-semibold leading-none ml-2">+${pourcentage}%</div>
                            </div>
                            <div class="text-sm font-medium text-gray-400">Total sold products</div>
                        </div>
                         
                    </div>
                    <a href="/dierenartsen" class="text-[#f84525] font-medium text-sm hover:text-red-800">View</a>
                </div>
                <div class="bg-white rounded-md border border-gray-100 p-6 shadow-md shadow-black/5">
                    <div class="flex justify-between mb-6">
                        <div>
                            <div class="text-2xl font-semibold mb-1">${dette}Fbu</div>
                            <div class="text-sm font-medium text-gray-400">Debt not yet paid</div>
                        </div>
                    </div>
                    <a href="" class="text-[#f84525] font-medium text-sm hover:text-red-800">View</a>
                </div>
            </div>
         <!-- component -->
        <div class="max-w-full mx-auto mb-10">

              <div class="w-full flex justify-between items-center mb-3 mt-12 pl-3">
                  <div>
                      <h3 class="text-lg font-semibold text-slate-800">Recently </h3>
                      <p class="text-slate-500">sold products items.</p>
                  </div>
                  <div class="mx-3">
                      <div class="w-full max-w-sm min-w-[200px] relative">
                      <div class="relative">
                          <input
                          id="searchInput"
                          class="bg-white w-full pr-11 h-10 pl-3 py-2 bg-transparent placeholder:text-slate-400 text-slate-700 text-sm border border-slate-200 rounded transition duration-300 ease focus:outline-none focus:border-slate-400 hover:border-slate-400 shadow-sm focus:shadow-md"
                          placeholder="Search for product..."
                          />
                          <button
                          class="absolute h-8 w-8 right-1 top-1 my-auto px-2 flex items-center bg-white rounded "
                          type="button"
                          >
                          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" class="w-8 h-8 text-slate-600">
                              <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                          </svg>
                          </button>
                      </div>
                      </div>
                  </div>
              </div>
        
              <div class="relative flex flex-col w-full h-full overflow-scroll text-gray-700 bg-white shadow-md rounded-lg bg-clip-border">
              <table class="w-full text-left table-auto min-w-max" id="sellHistoryTable">
                  <thead>
                  <tr class="border-b border-slate-300 bg-slate-50">
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Name</th>
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Quantity</th>
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Price per Item</th>
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Total Price</th>
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Sold at</th>
                      <th class="p-4 text-sm font-normal leading-none text-slate-500">Debt</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="vente" items="${sellhistory}">
    <tr class="hover:bg-slate-50">
        <td class="p-4 border-b border-slate-200 py-5">
            <p class="block font-semibold text-sm text-slate-800">${vente.product_name}</p>
        </td>
        <td class="p-4 border-b border-slate-200 py-5">
            <p class="text-sm text-slate-500">${vente.quantite}</p>
        </td>
        <td class="p-4 border-b border-slate-200 py-5">
            <p class="text-sm text-slate-500">${vente.prix}</p>
        </td>
        <td class="p-4 border-b border-slate-200 py-5">
            <p class="text-sm text-slate-500">${vente.montant_total}</p>
        </td>
        <td class="p-4 border-b border-slate-200 py-5">
            <p class="text-sm text-slate-500">
                <c:out value="${vente.paiement}" default="0" />
            </p>
        </td>
        <td class="p-4 border-b border-slate-200 py-5 text-red">
            <c:choose>
                <c:when test="${vente.reste_a_payer > 0}">
                    <p class="text-sm text-red-500">${vente.reste_a_payer}</p>
                </c:when>
                <c:otherwise>
                    <p class="text-sm text-green-500">${vente.reste_a_payer}</p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</c:forEach>

                  </tbody>
              </table>
        </div>



</div>
            <div class="grid grid-cols-1  gap-6 mb-6">
                <div class="bg-white border border-gray-100 shadow-md shadow-black/5 p-6 rounded-md lg:col-span-2">
                    <div class="flex justify-between mb-4 items-start">
                        <div class="font-medium">Journey Statistics</div>
                         <div class="dropdown">
                            <button type="button" class="dropdown-toggle text-gray-400 hover:text-gray-600"><i class="ri-more-fill"></i></button>
                            <ul class="dropdown-menu shadow-md shadow-black/5 z-30 hidden py-1.5 rounded-md bg-white border border-gray-100 w-full max-w-[140px]">
                                <li>
                                    <a href="#" class="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Profile</a>
                                </li>
                                <li>
                                    <a href="#" class="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Settings</a>
                                </li>
                                <li>
                                    <a href="#" class="flex items-center text-[13px] py-1.5 px-4 text-gray-600 hover:text-blue-500 hover:bg-gray-50">Logout</a>
                                </li>
                            </ul>
                        </div> 
                    </div>
                    



<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<canvas id="sales-chart"></canvas>

<script type="text/javascript">
    // Fonction pour récupérer les données de ventes par jour
    function fetchSalesByDay() {
        $.ajax({
            url: '/stock_managment/getSalesByDay', // URL de ton Servlet
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                // Traiter les données récupérées et alimenter le graphique
                const { labels, salesData, debtData, paymentData } = processSalesData(data);
                updateChart(labels, salesData, debtData, paymentData);
            },
            error: function (xhr, status, error) {
                console.log("Erreur lors de la récupération des données : " + error);
            }
        });
    }

    // Fonction pour traiter les données récupérées
    function processSalesData(data) {
        let labels = [];
        let salesData = [];
        let debtData = [];
        let paymentData = [];

        data.forEach(sale => {
            labels.push(sale.saleDate);  // Ajouter la date
            salesData.push(sale.totalSold);  // Ajouter le nombre total vendu pour cette date
            debtData.push(sale.totalDebt);  // Ajouter le montant total des dettes pour cette date
            paymentData.push(sale.totalPayment);  // Ajouter le montant total des paiements pour cette date
        });

        return { labels, salesData, debtData, paymentData };
    }

    // Fonction pour mettre à jour le graphique
    function updateChart(labels, salesData, debtData, paymentData) {
        const ctx = document.getElementById('sales-chart').getContext('2d');

        // Créer ou mettre à jour le graphique avec les données récupérées
        new Chart(ctx, {
            type: 'bar',  // Utilisation de type 'line' pour un graphique en courbe
            data: {
                labels: labels, // Dates
                datasets: [
                    {
                        label: 'Ventes par jour',
                        data: salesData, // Quantités vendues
                        borderColor: 'rgb(0, 38, 255)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderWidth: 2,
                        fill: true,
                        tension: 1,
                        borderRadius:20,
                    },
                    {
                        label: 'Dettes par jour',
                        data: debtData, // Montant des dettes
                        borderColor: 'rgb(255, 99, 132)',
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                         borderWidth: 2,
                        fill: true,
                        tension: 1,
                        borderRadius:20,
                    },
                    {
                        label: 'Paiements par jour',
                        data: paymentData, // Montant des paiements
                        borderColor: 'rgb(54, 162, 235)',
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderWidth: 2,
                        fill: true,
                        tension: 1,
                        borderRadius:20,
                    }
                ]
            },
            options: {
            responsive: true,
            plugins: {
                legend: {
                position: 'top',
                }},
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Quantity'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Jour'
                        }
                    }
                }
            }
        });
    }

    // Appeler la fonction pour récupérer les données au chargement de la page
    fetchSalesByDay();





$(document).ready(function() {
    $("#searchInput").on("keyup", function() {
        let query = $(this).val();
        
        $.ajax({
            url: "/stock_managment/searchSellHistory",
            type: "GET",
            data: { query: query },
            dataType: "json",
            success: function(response) {
                let tableBody = $("#sellHistoryTable tbody");
                tableBody.empty(); // Vider le tableau avant d'ajouter les nouveaux résultats
            if (typeof response === "string") {
            response = JSON.parse(response);
            }
        console.log("Données reçues après conversion :", response);
                response.forEach(function(vente) {
                    let debtClass = vente.reste_a_payer > 0 ? "text-red-500" : "text-green-500";
                    console.log(vente);
                    let row = `<tr class="hover:bg-slate-50">
                <td class="p-4 border-b border-slate-200 py-5">
                    <p class="block font-semibold text-sm text-slate-800">${vente.product_name || 'N/A'}</p>
                </td>
                <td class="p-4 border-b border-slate-200 py-5">
                    <p class="text-sm text-slate-500">${vente.quantite || '0'}</p>
                </td>
                <td class="p-4 border-b border-slate-200 py-5">
                    <p class="text-sm text-slate-500">${vente.prix || '0'}</p>
                </td>
                <td class="p-4 border-b border-slate-200 py-5">
                    <p class="text-sm text-slate-500">${vente.montant_total || '0'}</p>
                </td>
                <td class="p-4 border-b border-slate-200 py-5">
                    <p class="text-sm text-slate-500">${vente.paiement || '0'}</p>
                </td>
                <td class="p-4 border-b border-slate-200 py-5 text-red">
                    <p class="text-sm ${vente.reste_a_payer > 0 ? 'text-red-500' : 'text-green-500'}">
                        ${vente.reste_a_payer || '0'}
                    </p>
                </td>
            </tr>`;
                    console.log(row);
                    tableBody.append(row);

                });
            }
        });
    });
});





</script>


                </div>
            </div>
      
      <!-- End Content -->
  <%@ include file="./layouts/footer.jsp" %>