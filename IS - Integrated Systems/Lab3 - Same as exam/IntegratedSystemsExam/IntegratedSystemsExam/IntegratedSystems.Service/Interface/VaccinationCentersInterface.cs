using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Interface
{
    public interface VaccinationCentersInterface : IRepository<VaccinationCenter>
    {
        IEnumerable<VaccinationCenter> GetAll();
        VaccinationCenter Get(Guid? id);
        VaccinationCenter Insert(VaccinationCenter entity);
        List<VaccinationCenter> InsertMany(List<VaccinationCenter> entities);
        VaccinationCenter Update(VaccinationCenter entity);
        VaccinationCenter Delete(VaccinationCenter entity);

    }
}
