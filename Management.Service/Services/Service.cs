using Management.Domain.Entities;
using Management.Domain.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Gerenciamento.Service.Services
{
    public class GenericService<T> : IGenericService<T> where T: BaseEntity
    {
        private readonly IGenericRepository<T> _genericRepository; 
        public GenericService(IGenericRepository<T> genericRepository)
        {
            _genericRepository = genericRepository;
        }

        public bool delete(int id)
        {
            return _genericRepository.remove(id);
        }

        public List<T> get(int? id = null)
        {
            return _genericRepository.get(id)?.ToList();
        }

        public T insert(T entity)
        {
            return _genericRepository.insert(entity);
        }

        public T update(int id, T entity)
        {
            return _genericRepository.update(id, entity);
        }
    }
}
