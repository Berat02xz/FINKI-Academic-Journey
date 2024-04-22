using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Implementation
{
    public class VaccinationCentersImplementation : Interface.VaccinationCentersInterface
    {
        private readonly IRepository<VaccinationCenter> _repository;

        public VaccinationCentersImplementation(IRepository<VaccinationCenter> repository)
        {
            _repository = repository;
        }
        public VaccinationCenter Delete(VaccinationCenter entity)
        {
           return _repository.Delete(entity);
        }

        public VaccinationCenter Get(Guid? id)
        {
            return _repository.Get(id);
        }

        public IEnumerable<VaccinationCenter> GetAll()
        {
            return _repository.GetAll();
        }

        public VaccinationCenter Insert(VaccinationCenter entity)
        {
            return _repository.Insert(entity);
        }

        public List<VaccinationCenter> InsertMany(List<VaccinationCenter> entities)
        {
            return _repository.InsertMany(entities);
        }

        public VaccinationCenter Update(VaccinationCenter entity)
        {
            return _repository.Update(entity);
        }
    }
}
