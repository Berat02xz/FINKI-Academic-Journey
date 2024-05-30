using GemBox.Document;
using Microsoft.AspNetCore.Mvc;
using MVCAdminApplication.Models;
using Newtonsoft.Json;
using System.Text;
using MVCAdminApplication.Models;

namespace MVCAdminApplication.Controllers
{
    public class OrderController : Controller
    {

        public OrderController()
        {
            ComponentInfo.SetLicense("FREE-LIMITED-KEY");
        }

        public IActionResult Index()
        {
            HttpClient client = new HttpClient();
            string URL = "http://localhost:5054/api/Admin/GetAllOrders";
            HttpResponseMessage response = client.GetAsync(URL).Result;

            var data = response.Content.ReadAsAsync<List<Order>>().Result;
            return View(data);
        }

        public IActionResult Details(Guid Id)
        {
            HttpClient client = new HttpClient();
            string URL = "http://localhost:5054/api/Admin/GetDetailsForOrder";
            var model = new
            {
                Id = Id
            };
            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var data = response.Content.ReadAsAsync<Order>().Result;
            return View(data);
        }

        public IActionResult ExportInvoice(Guid id)
        {
            //COPIED FROM DETAILS
            HttpClient client = new HttpClient();
            string URL = "http://localhost:5054/api/Admin/GetDetailsForOrder";
            var model = new
            {
                Id = id
            };
            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var data = response.Content.ReadAsAsync<Order>().Result;
  
            if (data == null)
            {
                // Handle the error appropriately, e.g., log the error and return
                // You might want to throw an exception or return a default value here
                throw new Exception("Data is null");
            }
            //COPIED FROM DETAILS

            var templatePath = Path.Combine(Directory.GetCurrentDirectory(), "Invoice.docx");

            var document = DocumentModel.Load(templatePath);
            document.Content.Replace("{{InvoiceNumber}}", data.Id.ToString());
            document.Content.Replace("{{User}}", data.Owner.FirstName.ToString() +" " +data.Owner.LastName.ToString());
            document.Content.Replace("{{NumberOfMovies}}", data.ProductInOrders.Count.ToString());


            StringBuilder sb = new StringBuilder();
            var totalPrice = 0;
            foreach (var item in data.ProductInOrders)
            {
                sb.Append(item.OrderedProduct.Movie.MovieName + " with quantity " + item.Quantity + " with price " + item.OrderedProduct.Price + "$");
                //New Line
                sb.Append(Environment.NewLine);
                totalPrice += item.Quantity * (int)item.OrderedProduct.Price;
            }
            document.Content.Replace("{{MovieList}}", sb.ToString());
            document.Content.Replace("{{TotalPrice}}", totalPrice.ToString() + "$");  

            var stream = new MemoryStream();
            document.Save(stream, new PdfSaveOptions());
            return File(stream.ToArray(), new PdfSaveOptions().ContentType, "ExportedInvoice.pdf");
        }
    }
}
