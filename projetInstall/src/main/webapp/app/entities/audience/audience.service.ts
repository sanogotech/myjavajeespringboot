import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IAudience } from '@/shared/model/audience.model';

const baseApiUrl = 'api/audiences';

export default class AudienceService {
  public find(id: number): Promise<IAudience> {
    return new Promise<IAudience>((resolve, reject) => {
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

  //New ++
  public findByContentieuxId(id: number): Promise<IAudience[]> {
    return new Promise<IAudience[]>((resolve, reject) => {
      axios
        .get(`api/contentieux/${id}/audiences`)
        .then(res => {
          console.log(res);
		  //New
		  console.log("---resultat ***", res.data);
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

  public create(entity: IAudience): Promise<IAudience> {
    return new Promise<IAudience>((resolve, reject) => {
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

  public update(entity: IAudience): Promise<IAudience> {
    return new Promise<IAudience>((resolve, reject) => {
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
