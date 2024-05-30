using Microsoft.AspNetCore.Mvc;
using MVCAdminApplication.Models;
using Newtonsoft.Json;
using System.Text;
using GemBox.Document;
using ExcelDataReader;

namespace MVCAdminApplication.Controllers
{
    public class UserController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult ImportUsers(IFormFile file)
        {
            string pathToUpload = $"{Directory.GetCurrentDirectory()}\\files\\{file.FileName}";

            using (FileStream fileStream = System.IO.File.Create(pathToUpload))
            {
                file.CopyTo(fileStream); ;
                fileStream.Flush();
            }

            List<EShopApplicationUser> users = getAllUsersFromFile(file.FileName);

            HttpClient client = new HttpClient();
            string URL = "http://localhost:5180/api/Admin/ImportAllUsers";

            HttpContent content = new StringContent(JsonConvert.SerializeObject(users), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var data = response.Content.ReadAsAsync<bool>().Result;
            return RedirectToAction("Index", "Order");

        }

        private List<EShopApplicationUser> getAllUsersFromFile(string fileName)
        {
            List<EShopApplicationUser> users = new List<EShopApplicationUser>();
            string filePath = $"{Directory.GetCurrentDirectory()}\\files\\{fileName}";
            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);

            using (var stream = System.IO.File.Open(filePath, FileMode.Open, FileAccess.Read))
            {


                using (var reader = ExcelReaderFactory.CreateReader(stream))
                {
                    while (reader.Read())
                    {
                        users.Add(new EShopApplicationUser
                        {
                            Email = reader.GetValue(0).ToString(),
                            Password = reader.GetValue(1).ToString(),
                            ConfirmPassword = reader.GetValue(2).ToString()
                        });

                    }
                }
            }
            return users;
        }
    }
}
