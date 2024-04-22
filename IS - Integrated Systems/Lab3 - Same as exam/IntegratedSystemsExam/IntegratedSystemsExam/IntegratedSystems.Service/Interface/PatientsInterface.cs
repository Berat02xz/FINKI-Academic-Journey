using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface PatientsInterface : IRepository<Patient> 
    {
        IEnumerable<Patient> GetAll();
        Patient Get(Guid? id);
        Patient Insert(Patient entity);
        List<Patient> InsertMany(List<Patient> entities);
        Patient Update(Patient entity);
        Patient Delete(Patient entity);
    }
}
