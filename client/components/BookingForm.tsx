"use client";

import { useState } from "react";
import { Icon } from "@/components/Icon";

const vehicles = [
  { id: "black", name: "Velocity Black", tier: "Premium • 4 min away", price: "$42.50", icon: "electric_car" },
  { id: "standard", name: "Velocity Standard", tier: "Regular • 6 min away", price: "$28.90", icon: "directions_car" },
] as const;

function Field({
  label,
  icon,
  children,
}: {
  label?: string;
  icon?: string;
  children: React.ReactNode;
}) {
  return (
    <div className="space-y-xs">
      {label ? <label className="ml-xs text-label-sm text-on-surface-variant">{label}</label> : null}
      <div className="input-focus-ring flex items-center gap-sm rounded-xl border border-outline-variant/30 bg-surface-container-lowest/40 p-md transition-colors focus-within:border-primary">
        {icon ? <Icon name={icon} className="text-on-surface-variant" /> : null}
        {children}
      </div>
    </div>
  );
}

export function BookingForm() {
  const [selected, setSelected] = useState<string>("black");

  return (
    <div className="flex max-h-[85vh] w-full flex-col overflow-hidden rounded-[2rem] border border-outline-variant/30 shadow-[0_32px_64px_-12px_rgba(0,0,0,0.5)] glass-panel">
      {/* Panel header */}
      <div className="border-b border-outline-variant/20 p-xl">
        <div className="mb-xs flex items-center justify-between">
          <h2 className="text-headline-lg font-bold text-on-surface">Where to next?</h2>
          <div className="flex gap-xs">
            <span className="h-2 w-2 animate-pulse rounded-full bg-primary/40" />
            <span className="h-2 w-2 rounded-full bg-primary/20" />
          </div>
        </div>
        <p className="text-body-md text-on-surface-variant">Configure your premium high-velocity transit.</p>
      </div>

      {/* Form body */}
      <div className="sidebar-scroll flex-grow space-y-lg overflow-y-auto p-xl">
        {/* Pickup */}
        <section className="space-y-md">
          <div className="flex items-center gap-sm">
            <span className="h-2 w-2 rounded-full bg-primary ring-4 ring-primary/20" />
            <span className="text-label-md uppercase tracking-wider text-on-surface-variant">Pickup Location</span>
          </div>
          <div className="grid grid-cols-2 gap-md">
            <Field label="Latitude">
              <input
                className="w-full border-none bg-transparent p-0 text-body-md text-on-surface focus:ring-0"
                defaultValue="40.7128° N"
                aria-label="Pickup latitude"
              />
            </Field>
            <Field label="Longitude">
              <input
                className="w-full border-none bg-transparent p-0 text-body-md text-on-surface focus:ring-0"
                defaultValue="74.0060° W"
                aria-label="Pickup longitude"
              />
            </Field>
          </div>
        </section>

        {/* Dropoff */}
        <section className="space-y-md">
          <div className="flex items-center gap-sm">
            <span className="h-2 w-2 rounded-sm bg-on-surface ring-4 ring-on-surface/10" />
            <span className="text-label-md uppercase tracking-wider text-on-surface-variant">Dropoff Destination</span>
          </div>
          <Field icon="search">
            <input
              className="w-full border-none bg-transparent p-0 text-body-md text-on-surface focus:ring-0"
              placeholder="Enter destination..."
              aria-label="Dropoff destination"
            />
          </Field>
        </section>

        {/* Vehicle class */}
        <section className="space-y-md pt-base">
          <span className="text-label-md uppercase tracking-wider text-on-surface-variant">Vehicle Class</span>
          <div className="grid grid-cols-1 gap-sm" role="radiogroup" aria-label="Vehicle class">
            {vehicles.map((v) => {
              const active = selected === v.id;
              return (
                <button
                  key={v.id}
                  type="button"
                  role="radio"
                  aria-checked={active}
                  onClick={() => setSelected(v.id)}
                  className={`flex items-center justify-between rounded-2xl p-md text-left transition-all ${
                    active
                      ? "border-2 border-primary bg-primary/10 shadow-[0_0_20px_rgba(198,198,198,0.05)]"
                      : "border border-outline-variant/30 bg-surface-container-lowest/30 hover:bg-surface-container-highest/40"
                  }`}
                >
                  <div className="flex items-center gap-md">
                    <div
                      className={`flex h-14 w-14 items-center justify-center rounded-xl ${active ? "bg-on-primary/10" : "bg-surface-container-highest/50"}`}
                    >
                      <Icon name={v.icon} size={32} className={active ? "text-primary" : "text-on-surface-variant"} />
                    </div>
                    <div>
                      <h4 className="text-label-md text-on-surface">{v.name}</h4>
                      <p className="text-label-sm text-on-surface-variant">{v.tier}</p>
                    </div>
                  </div>
                  <p className={`text-headline-md ${active ? "text-primary" : "text-on-surface"}`}>{v.price}</p>
                </button>
              );
            })}
          </div>
        </section>

        {/* Scheduling */}
        <section className="space-y-md">
          <span className="text-label-md uppercase tracking-wider text-on-surface-variant">Departure Time</span>
          <div className="flex items-center gap-sm">
            <div className="input-focus-ring flex flex-grow items-center justify-between rounded-xl border border-outline-variant/30 bg-surface-container-lowest/40 p-md">
              <Icon name="calendar_month" size={20} className="text-on-surface-variant" />
              <span className="text-body-md text-on-surface">Aug 15, 2024</span>
              <Icon name="expand_more" className="text-on-surface-variant" />
            </div>
            <div className="input-focus-ring flex flex-grow items-center justify-between rounded-xl border border-outline-variant/30 bg-surface-container-lowest/40 p-md">
              <Icon name="schedule" size={20} className="text-on-surface-variant" />
              <span className="text-body-md text-on-surface">09:00 PM</span>
              <Icon name="expand_more" className="text-on-surface-variant" />
            </div>
          </div>
        </section>
      </div>

      {/* Footer CTA */}
      <div className="border-t border-outline-variant/20 bg-surface-container/30 p-xl backdrop-blur-sm">
        <div className="mb-lg flex items-center justify-between px-xs">
          <div className="flex items-center gap-sm">
            <Icon name="credit_card" className="text-on-surface-variant" />
            <span className="text-label-md text-on-surface">Personal Card •••• 4242</span>
          </div>
          <button className="text-label-sm font-bold text-primary">CHANGE</button>
        </div>
        <button className="w-full rounded-2xl bg-primary py-xl text-headline-md text-on-primary shadow-xl transition-all hover:brightness-110 active:scale-[0.98]">
          Confirm Velocity Request
        </button>
      </div>
    </div>
  );
}
