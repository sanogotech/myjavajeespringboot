import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IContentieux } from '@/shared/model/contentieux.model';

const baseApiUrl = 'api/contentieuxes';

export default class ContentieuxService {
  public find(id: number): Promise<IContentieux> {
    return new Promise<IContentieux>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }


// NEW TODO
   public retrieveByContentieux(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`api/contentieuxesAllById/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }


    
  
  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IContentieux): Promise<IContentieux> {
    return new Promise<IContentieux>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IContentieux): Promise<IContentieux> {
    return new Promise<IContentieux>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
