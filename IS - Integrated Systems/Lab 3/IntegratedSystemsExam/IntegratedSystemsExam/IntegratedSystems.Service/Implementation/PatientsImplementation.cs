using IntegratedSystems.Domain.Domain_Models;
using IntegratedSystems.Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegratedSystems.Service.Implementation
{
    //this class is an implementation of the PatientsInterface
    //
    public class PatientsImplementation : Interface.PatientsInterface
    {
        private readonly IRepository<Patient> _repository;

        public PatientsImplementation(IRepository<Patient> repository)
        {
            _repository = repository;
        }

        public Patient Delete(Patient entity)
        {
            return _repository.Delete(entity);
        }

        public Patient Get(Guid? id)
        {
            return _repository.Get(id);
        }

        public IEnumerable<Patient> GetAll()
        {
            return _repository.GetAll();
        }

        public Patient Insert(Patient entity)
        {
            return _repository.Insert(entity);
        }

        public List<Patient> InsertMany(List<Patient> entities)
        {
            return _repository.InsertMany(entities);
        }

        public Patient Update(Patient entity)
        {
            return _repository.Update(entity);
        }
    }
}
