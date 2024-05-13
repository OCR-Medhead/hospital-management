import { Specialization } from "./specialization"

export interface HospitalSpecialization {
    id: number,
    name: string,
    specializations: Specialization[]
}
